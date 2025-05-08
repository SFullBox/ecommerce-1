package com.senai.ecommerce.entities;

import com.fasterxml.jackson.annotation.JsonValue;

public enum StatusDoPedido {

	AGUARDANDO_PAGAMENTO,
	PAGO,
	ENVIADO,
	ENTREGUE,
	CANCELADO;
	
	@JsonValue
	public String getValue() {
		return this.name();
	}
}
