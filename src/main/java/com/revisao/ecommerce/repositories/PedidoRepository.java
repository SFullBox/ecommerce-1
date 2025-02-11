package com.revisao.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revisao.ecommerce.entities.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

}
