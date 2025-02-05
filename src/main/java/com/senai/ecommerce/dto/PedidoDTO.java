package com.senai.ecommerce.dto;

import java.time.Instant;

import com.senai.ecommerce.enums.StatusDoPedido;

public record PedidoDTO( Instant momento, StatusDoPedido status) {

}
