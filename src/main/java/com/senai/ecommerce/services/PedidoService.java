package com.senai.ecommerce.services;

import java.time.Instant;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.senai.ecommerce.dto.PedidoDTO;
import com.senai.ecommerce.entities.Categoria;
import com.senai.ecommerce.entities.Pedido;
import com.senai.ecommerce.entities.Usuario;
import com.senai.ecommerce.enums.StatusDoPedido;
import com.senai.ecommerce.repositories.PedidoRepository;
import com.senai.ecommerce.repositories.UsuarioRepository;

import jakarta.transaction.Transactional;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	
	public PedidoDTO insert(PedidoDTO pedidoDTO) {
		Pedido pedido = new Pedido();
		pedido.setMomento(Instant.now());
		pedido.setStatus(StatusDoPedido.AGUARDANDO_PAGAMENTO);
		Usuario usuario = usuarioRepository.getReferenceById(pedidoDTO.getClientId());
		
		pedido.setCliente(usuario);
		pedido = pedidoRepository.save(pedido);
		
		return new PedidoDTO(pedido);
	}
	
}
