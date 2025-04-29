package com.senai.ecommerce.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.senai.ecommerce.entities.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    
    @Query("SELECT p FROM Pedido p LEFT JOIN FETCH p.items WHERE p.id = :id")
    Pedido findPedidoComItens(@Param("id") Long id);

    List<Pedido> findByClienteId(Long clienteId);

    @Query("SELECT DISTINCT p FROM Pedido p LEFT JOIN FETCH p.items WHERE p IN :pedidos")
    List<Pedido> findPedidosWithItems(List<Pedido> pedidos);
    
    @Query("SELECT p FROM Pedido p LEFT JOIN FETCH p.items")
    List<Pedido> findAllWithItems();
}