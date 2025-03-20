package com.senai.ecommerce.entities;

import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.senai.ecommerce.enums.StatusDoPedido;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity(name = "tb_pedido")
@Table(name ="tb_pedido")
public class Pedido {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Instant momento;
	
	
	private StatusDoPedido status;
	
	@OneToMany(mappedBy = "id.pedido")
	private Set<ItemDoPedido> items = new HashSet<>();
	

	
	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Usuario cliente;
	
	@OneToOne(mappedBy = "pedido", cascade = CascadeType.ALL)
	private Pagamento pagamento;

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

	public Usuario getCliente() {
		return cliente;
	}

	public void setCliente(Usuario cliente) {
		this.cliente = cliente;
	}



	public Pedido(Long id, Instant momento, StatusDoPedido status, Usuario cliente, Pagamento pagamento) {
		super();
		this.id = id;
		this.momento = momento;
		this.status = status;
		this.cliente = cliente;
		this.pagamento = pagamento;
	}

	public Pedido() {
		super();
	}
	
	
	
	
	public Set<ItemDoPedido> getItems() {
		return items;
	}
	
	public List<Produto> getProduto(){
		return items.stream().map(x -> x.getProduto()).toList();
	}
	

	
	
	
	
}
