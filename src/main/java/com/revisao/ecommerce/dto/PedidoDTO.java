package com.revisao.ecommerce.dto;

import java.time.Instant;

import com.revisao.ecommerce.entities.StatusDoPedido;

public record PedidoDTO(Instant momento, StatusDoPedido status) {

}
