package com.senai.ecommerce.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class ItemPedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;    

    @ManyToOne
    private Pedido pedido;

    @ManyToOne
    private Produto produto;
    
    private Integer quantidade;

    private Double preco;
    
    public ItemPedido() {
    }

    public ItemPedido(Pedido pedido, Produto produto, Integer quantidade, Double preco) {
        this.pedido = pedido;
        this.produto = produto;
        this.quantidade = quantidade;
        this.preco = preco;
    }       
    
    public Long getId() {   
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
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
}
