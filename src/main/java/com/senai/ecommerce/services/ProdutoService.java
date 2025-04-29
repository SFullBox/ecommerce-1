package com.senai.ecommerce.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.senai.ecommerce.dto.CategoriaDTO;
import com.senai.ecommerce.dto.ProdutoDTO;
import com.senai.ecommerce.entities.Categoria;
import com.senai.ecommerce.entities.Produto;
import com.senai.ecommerce.repositories.CategoriaRepository;
import com.senai.ecommerce.repositories.ProdutoRepository;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<ProdutoDTO> findAll() {
        List<Produto> list = produtoRepository.findAll();
        return list.stream().map(ProdutoDTO::new).collect(Collectors.toList());
    }

    public Page<ProdutoDTO> findAll(Pageable pageable) {
        Page<Produto> page = produtoRepository.findAll(pageable);
        return page.map(ProdutoDTO::new);
    }

    @Transactional
    public ProdutoDTO insert(ProdutoDTO dto) {
        Produto entity = new Produto();
        entity.setNome(dto.getNome());
        entity.setDescricao(dto.getDescricao());
        entity.setPreco(dto.getPreco());
        entity.setImagemUrl(dto.getImagemUrl());

        if (dto.getCategorias() != null && !dto.getCategorias().isEmpty()) {
            for (CategoriaDTO cat : dto.getCategorias()) {
                Categoria categoria = categoriaRepository.findById(cat.getId())
                    .orElseThrow(() -> new RuntimeException("Categoria não encontrada!"));
                entity.getCategorias().add(categoria);
            }
        }

        entity = produtoRepository.save(entity);
        return new ProdutoDTO(entity);
    }

    @Transactional
    public ProdutoDTO update(Long id, ProdutoDTO dto) {
        Produto entity = produtoRepository.getReferenceById(id);
        entity.setNome(dto.getNome());
        entity.setDescricao(dto.getDescricao());
        entity.setPreco(dto.getPreco());
        entity.setImagemUrl(dto.getImagemUrl());

        // Limpar categorias existentes
        entity.getCategorias().clear();

        if (dto.getCategorias() != null && !dto.getCategorias().isEmpty()) {
            for (CategoriaDTO cat : dto.getCategorias()) {
                Categoria categoria = categoriaRepository.findById(cat.getId())
                    .orElseThrow(() -> new RuntimeException("Categoria não encontrada!"));
                entity.getCategorias().add(categoria);
            }
        }

        entity = produtoRepository.save(entity);
        return new ProdutoDTO(entity);
    }

    @Transactional
    public void delete(Long id) {
        produtoRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public ProdutoDTO findById(Long id) {
        Produto produto = produtoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
        return new ProdutoDTO(produto);
    }
}
