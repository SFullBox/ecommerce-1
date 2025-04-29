package com.senai.ecommerce.dto;

import java.util.ArrayList;
import java.util.List;

public class CarrinhoDTO {
    private Long produtoId;
    private Integer quantidade;
    private List<ItemCarrinhoDTO> itens = new ArrayList<>();

    public CarrinhoDTO() {
    }

    public Long getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Long produtoId) {
        this.produtoId = produtoId;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public List<ItemCarrinhoDTO> getItens() {
        return itens;
    }

    public void setItens(List<ItemCarrinhoDTO> itens) {
        this.itens = itens;
    }
} 