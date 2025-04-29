package com.senai.ecommerce.dto;

import com.senai.ecommerce.entities.ItemDoPedido;

public class ItemPedidoDTO {
    private Long produtoId;
    private String nomeProduto;
    private Integer quantidade;
    private Double preco;
    private String imagemUrl;

    public ItemPedidoDTO() {
    }

    public ItemPedidoDTO(ItemDoPedido item) {
        this.produtoId = item.getProduto().getId();
        this.nomeProduto = item.getProduto().getNome();
        this.quantidade = item.getQuantidade();
        this.preco = item.getPreco();
        this.imagemUrl = item.getProduto().getImagemUrl();
    }

    public Long getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Long produtoId) {
        this.produtoId = produtoId;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public String getImagemUrl() {
        return imagemUrl;
    }

    public void setImagemUrl(String imagemUrl) {
        this.imagemUrl = imagemUrl;
    }

    public Double getSubTotal() {
        return preco * quantidade;
    }
} 