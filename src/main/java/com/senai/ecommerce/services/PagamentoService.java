package com.senai.ecommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.senai.ecommerce.dto.PagamentoDTO;
import com.senai.ecommerce.entities.Pagamento;
import com.senai.ecommerce.entities.Pedido;
import com.senai.ecommerce.entities.StatusDoPedido;
import com.senai.ecommerce.repositories.PedidoRepository;

@Service
public class PagamentoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Transactional
    public PagamentoDTO processarPagamento(Long pedidoId) {
        Pedido pedido = pedidoRepository.findPedidoComItens(pedidoId);
        
        if (pedido == null) {
            throw new RuntimeException("Pedido não encontrado");
        }

        if (pedido.getStatus() == StatusDoPedido.CANCELADO) {
            throw new RuntimeException("Não é possível processar o pagamento. Este pedido foi cancelado.");
        }

        if (pedido.getStatus() == StatusDoPedido.PAGO) {
            throw new RuntimeException("Este pedido já foi pago. Não é possível processar o pagamento novamente.");
        }

        if (pedido.getStatus() != StatusDoPedido.AGUARDANDO_PAGAMENTO) {
            throw new RuntimeException("Este pedido não está aguardando pagamento. Status atual: " + pedido.getStatus());
        }

        if (pedido.getPagamento() != null) {
            throw new RuntimeException("Este pedido já possui um pagamento registrado.");
        }

        Pagamento pagamento = new Pagamento();
        pagamento.setMomento(java.time.Instant.now());
        pagamento.setPedido(pedido);
        pedido.setPagamento(pagamento);
        pedido.setStatus(StatusDoPedido.PAGO);

        pedido = pedidoRepository.save(pedido);

        PagamentoDTO dto = new PagamentoDTO();
        dto.setId(pedido.getPagamento().getId());
        dto.setMomento(pedido.getPagamento().getMomento());
        dto.setPedidoId(pedido.getId());

        return dto;
    }
} 