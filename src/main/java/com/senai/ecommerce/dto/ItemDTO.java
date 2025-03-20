package com.senai.ecommerce.dto;

import com.senai.ecommerce.entities.ItemDoPedido;
import com.senai.ecommerce.entities.ItemDoPedidoPK;

public class ItemDTO {
	
	private ItemDoPedidoPK id;
	private Integer quantidade;
	private Double preco;
	
	public ItemDTO() {}

	public ItemDTO(ItemDoPedidoPK id, Integer quantidade, Double preco) {
		this.id = id;
		this.quantidade = quantidade;
		this.preco = preco;
	}

	public ItemDTO(ItemDoPedido itemPedido) {
		// TODO Auto-generated constructor stub
	}

	public ItemDTO(ItemDTO itemDTO) {
		// TODO Auto-generated constructor stub
	}

	public ItemDoPedidoPK getId() {
		return id;
	}

	public void setId(ItemDoPedidoPK id) {
		this.id = id;
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
