package com.senai.ecommerce.dto;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.senai.ecommerce.entities.Pedido;
import com.senai.ecommerce.entities.StatusDoPedido;

public class PedidoDTO {

	private Long id;
	private Instant momento;
	private StatusDoPedido status;
	private List<ItemPedidoDTO> items = new ArrayList<>();
	private Double total;
	
	public PedidoDTO() {
		
	}
	
	public PedidoDTO(Pedido pedido) {
		this.id = pedido.getId();
		this.momento = pedido.getMomento();
		this.status = pedido.getStatus();
		this.total = pedido.getTotal();
		
		// Converter os itens do pedido para DTO
		this.items = pedido.getItems().stream()
			.map(ItemPedidoDTO::new)
			.collect(Collectors.toList());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Instant getMomento() {
		return momento;
	}

	public void setMomento(Instant momento) {
		this.momento = momento;
	}

	public StatusDoPedido getStatus() {
		return status;
	}

	public void setStatus(StatusDoPedido status) {
		this.status = status;
	}

	public List<ItemPedidoDTO> getItems() {
		return items;
	}

	public void setItems(List<ItemPedidoDTO> items) {
		this.items = items;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}
	
	
	
}
