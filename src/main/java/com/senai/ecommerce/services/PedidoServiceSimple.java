package com.senai.ecommerce.services;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.senai.ecommerce.enums.StatusDoPedido;
import com.senai.ecommerce.repositories.PedidoSpecificRepository;

@Service
public class PedidoServiceSimple {
	
	@Autowired
	private PedidoSpecificRepository repo;
	
	public ResponseEntity createOrderPerUserService(Instant moment, StatusDoPedido status, Long clientId) throws Exception {
		return repo.createOrderPerUser(moment, status, clientId);
	}
	
		
		
}
