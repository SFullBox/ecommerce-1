package com.senai.ecommerce.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.senai.ecommerce.entities.ItemDoPedido;
import com.senai.ecommerce.entities.ItemDoPedidoPK;


@Repository
public interface ItemRepository extends JpaRepository<ItemDoPedido, ItemDoPedidoPK> {
    // Método para buscar um ItemDoPedido específico usando a chave composta
    Optional<ItemDoPedido> findById(ItemDoPedidoPK itemDoPedidoPK);
}
