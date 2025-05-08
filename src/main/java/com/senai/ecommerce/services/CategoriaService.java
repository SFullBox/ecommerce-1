package com.senai.ecommerce.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.senai.ecommerce.dto.CategoriaDTO;
import com.senai.ecommerce.entities.Categoria;
import com.senai.ecommerce.repositories.CategoriaRepository;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<CategoriaDTO> findAll() {
        List<Categoria> list = categoriaRepository.findAll();
        return list.stream().map(CategoriaDTO::new).collect(Collectors.toList());
    }

    @Transactional
    public CategoriaDTO insert(CategoriaDTO dto) {
        Categoria entity = new Categoria();
        entity.setNome(dto.getNome());
        entity = categoriaRepository.save(entity);
        return new CategoriaDTO(entity);
    }

    @Transactional
    public CategoriaDTO update(Long id, CategoriaDTO dto) {
        Categoria entity = categoriaRepository.getReferenceById(id);
        entity.setNome(dto.getNome());
        entity = categoriaRepository.save(entity);
        return new CategoriaDTO(entity);
    }

    @Transactional
    public void delete(Long id) {
        categoriaRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public CategoriaDTO findById(Long id) {
        Categoria categoria = categoriaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));
        return new CategoriaDTO(categoria);
    }
} 