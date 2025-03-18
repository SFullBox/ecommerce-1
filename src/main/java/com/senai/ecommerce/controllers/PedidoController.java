package com.senai.ecommerce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.senai.ecommerce.dto.PedidoDTO;
import com.senai.ecommerce.services.PedidoServiceSimple;

@RestController
@RequestMapping("/pedido")
public class PedidoController {
	
	
	
	@Autowired
	private PedidoServiceSimple service;
	
	@PostMapping("create")
	public ResponseEntity createOrderPerUser(@RequestBody PedidoDTO data) throws Exception {
		return  service.createOrderPerUserService(data.momento(),data.status(),data.clientId());
	}
}
