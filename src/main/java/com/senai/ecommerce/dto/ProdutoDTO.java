package com.senai.ecommerce.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.senai.ecommerce.entities.Produto;

public class ProdutoDTO {

    private Long id;
    private String nome;
    private String descricao;
    private Double preco;
    private String imagemUrl;
    private List<CategoriaDTO> categorias;

    public ProdutoDTO() {
    }

    public ProdutoDTO(Long id, String nome, String descricao, Double preco, String imagemUrl) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.imagemUrl = imagemUrl;
    }

    public ProdutoDTO(Produto produto) {
        this.id = produto.getId();
        this.nome = produto.getNome();
        this.descricao = produto.getDescricao();
        this.preco = produto.getPreco();
        this.imagemUrl = produto.getImagemUrl();
        this.categorias = produto.getCategorias().stream()
                                .map(CategoriaDTO::new)
                                .collect(Collectors.toList());
    }

    // Getters e Setters


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

    public String getImagemUrl() {
        return imagemUrl;
    }

    public void setImagemUrl(String imagemUrl) {
        this.imagemUrl = imagemUrl;
    }

    public List<CategoriaDTO> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<CategoriaDTO> categorias) {
        this.categorias = categorias;
    }
    
    
}
