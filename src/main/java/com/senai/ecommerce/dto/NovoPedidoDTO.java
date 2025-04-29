package com.senai.ecommerce.dto;

import java.util.ArrayList;
import java.util.List;

public class NovoPedidoDTO {
    private Long clienteId;
    private List<ItemPedidoDTO> itens = new ArrayList<>();

    public NovoPedidoDTO() {
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public List<ItemPedidoDTO> getItens() {
        return itens;
    }

    public void setItens(List<ItemPedidoDTO> itens) {
        this.itens = itens;
    }
} 