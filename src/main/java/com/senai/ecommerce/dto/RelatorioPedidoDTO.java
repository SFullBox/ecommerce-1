package com.senai.ecommerce.dto;

import com.senai.ecommerce.entities.Pedido;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class RelatorioPedidoDTO {

    private Long pedido;
    private String cliente;
    private String status;
    private String momento;

    public RelatorioPedidoDTO(Pedido pedido) {
        this.pedido = pedido.getId();
        this.cliente = pedido.getCliente().getEmail();
        this.status = pedido.getStatus().toString();

        // ele tá passando o memento que é Instant para string no formato dd/mm/aaaa
        // em string
        this.momento = pedido.getMomento().atZone(ZoneId.systemDefault())
                .format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public Long getPedido() {
        return pedido;
    }

    public String getCliente() {
        return cliente;
    }

    public String getStatus() {
        return status;
    }

    public String getMomento() {
        return momento;
    }
}
