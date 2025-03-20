package com.senai.ecommerce.dto;

import java.time.Instant;

import com.senai.ecommerce.entities.Pedido;
import com.senai.ecommerce.enums.StatusDoPedido;

public class PedidoDTO{
	private Long id;
	private Instant momento;
	private StatusDoPedido status;
	private Long clientId;
	
	
	
	
	public PedidoDTO() {
		super();
	}

	public PedidoDTO(Long id, Instant momento, StatusDoPedido status, Long clientId) {
		super();
		this.id = id;
		this.momento = momento;
		this.status = status;
		this.clientId = clientId;
	}
	
	public PedidoDTO(Pedido entity) {
		super();
		id =entity.getId();
		momento = entity.getMomento();
		status = entity.getStatus();
		clientId =entity.getCliente().getId();
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

	public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}
	
	
	
	
	
	
	




}
