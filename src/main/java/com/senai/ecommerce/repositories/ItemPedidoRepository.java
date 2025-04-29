package com.senai.ecommerce.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.senai.ecommerce.entities.ItemDoPedido;

@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemDoPedido, Long> {
    
    @Query("SELECT i FROM ItemDoPedido i WHERE i.id.pedido.id = :pedidoId")
    List<ItemDoPedido> findByPedidoId(@Param("pedidoId") Long pedidoId);
}
