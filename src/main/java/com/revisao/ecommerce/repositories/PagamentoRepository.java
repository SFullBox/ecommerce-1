package com.revisao.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revisao.ecommerce.entities.Pagamento;

public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {

}
