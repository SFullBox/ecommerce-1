package com.senai.ecommerce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.senai.ecommerce.dto.PedidoDTO;
import com.senai.ecommerce.services.PedidoService;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
	
	
	
	@Autowired
	private PedidoService pedidoService;
	
	@PostMapping("/creating")
	public ResponseEntity<PedidoDTO> insert(@RequestBody PedidoDTO pedidoDTO){
		
		pedidoDTO = pedidoService.insert(pedidoDTO);
		return ResponseEntity.ok(pedidoDTO);
		
	}
}
