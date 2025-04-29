package com.senai.ecommerce.entities;

import java.io.Serializable;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_item_pedido")
public class ItemDoPedido implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ItemDoPedidoPK id = new ItemDoPedidoPK();
	
	private Integer quantidade;
	private Double preco;
	private Double subTotal;
	
	public ItemDoPedido() {
		
	}
	
	public ItemDoPedido(Pedido pedido, Produto produto, Integer quantidade, Double preco) {
		id.setPedido(pedido);
		id.setProduto(produto);
		this.quantidade = quantidade;
		this.preco = preco;
		this.subTotal = quantidade * preco;
	}

	public Pedido getPedido() {
		return id.getPedido();
	}

	public void setPedido(Pedido pedido) {
		id.setPedido(pedido);
	}
	
	public Produto getProduto() {
		return id.getProduto();
	}

	public void setProduto(Produto produto) {
		id.setProduto(produto);
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
		this.subTotal = quantidade * preco;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
		this.subTotal = quantidade * preco;
	}

	public Double getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(Double subTotal) {
		this.subTotal = subTotal;
	}
	
	
	
	

}
