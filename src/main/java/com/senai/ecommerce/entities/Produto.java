package com.senai.ecommerce.entities;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity(name="tb_produto")
@Table(name="tb_produto")
public class Produto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String descricao;
	private Double preco;
	private String imgUrl;
	
	@ManyToMany
	@JoinTable(name = "tb_produto_categoria", joinColumns = 
	@JoinColumn(name = "produto_id"),inverseJoinColumns = 
	@JoinColumn(name = "categoria_id"))
	private Set<Categoria> categorias = new HashSet<>();
	
	@OneToMany(mappedBy = "id.produto")
	private Set<ItemDoPedido> items = new HashSet<>();
	
	
	public Produto(Long id, String nome, String descricao, Double preco, String imgUrl, Set<Categoria> categorias) {
		super();
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.preco = preco;
		this.imgUrl = imgUrl;
		this.categorias = categorias;
	}
	public Produto() {
		super();
	}
	

	
	public Set<Categoria> getCategorias() {
		return categorias;
	}
	public void setCategorias(Set<Categoria> categorias) {
		this.categorias = categorias;
	}

	public void setItems(Set<ItemDoPedido> items) {
		this.items = items;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Double getPreco() {
		return preco;
	}
	public void setPreco(Double preco) {
		this.preco = preco;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public Set<ItemDoPedido> getItems() {
		return items;
	}
	
	public List<Pedido> getPedido(){
		return items.stream().map(x -> x.getPedido()).toList();
	}
	
	
	
	

	
}
