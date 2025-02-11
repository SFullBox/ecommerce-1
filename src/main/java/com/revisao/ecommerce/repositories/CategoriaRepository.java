package com.revisao.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revisao.ecommerce.entities.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}
