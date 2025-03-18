package com.senai.ecommerce.dto;

import com.senai.ecommerce.entities.Categoria;

public class CategoriaDTO {
	private Long id;
	private String name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CategoriaDTO() {
		
	}
	public CategoriaDTO(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	public CategoriaDTO(Categoria entity) {
		id = entity.getId();
		name = entity.getNome();
	}

}
