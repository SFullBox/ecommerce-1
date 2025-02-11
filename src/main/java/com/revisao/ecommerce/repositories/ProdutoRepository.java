package com.revisao.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revisao.ecommerce.entities.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
