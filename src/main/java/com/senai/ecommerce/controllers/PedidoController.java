package com.senai.ecommerce.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.senai.ecommerce.dto.ItemPedidoDTO;
import com.senai.ecommerce.dto.PedidoDTO;
import com.senai.ecommerce.services.PedidoService;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoController {

	@Autowired
	private PedidoService pedidoService;
	
	@GetMapping
	public ResponseEntity<List<PedidoDTO>> findAll() {
		List<PedidoDTO> pedidos = pedidoService.findAll();
		return ResponseEntity.ok(pedidos);
	}
	
	@PostMapping
	public ResponseEntity<PedidoDTO> insert(@RequestBody PedidoDTO dto) {
		PedidoDTO pedido = pedidoService.insert(dto);
		return ResponseEntity.ok(pedido);
	}

	@PutMapping("/{id}")
	public ResponseEntity<PedidoDTO> update(@PathVariable Long id, @RequestBody PedidoDTO dto) {
		PedidoDTO pedido = pedidoService.update(id, dto);
		return ResponseEntity.ok(pedido);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		pedidoService.delete(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/{id}")
	public ResponseEntity<PedidoDTO> findById(@PathVariable Long id) {
		PedidoDTO pedido = pedidoService.findById(id);
		return ResponseEntity.ok(pedido);
	}

	@GetMapping("/cliente/{clienteId}")
	public ResponseEntity<List<PedidoDTO>> findByCliente(@PathVariable Long clienteId) {
		List<PedidoDTO> pedidos = pedidoService.findByCliente(clienteId);
		return ResponseEntity.ok(pedidos);
	}

	@GetMapping("/{id}/itens")
	public ResponseEntity<List<ItemPedidoDTO>> findItensPedido(@PathVariable Long id) {
		List<ItemPedidoDTO> itens = pedidoService.findItensPedido(id);
		return ResponseEntity.ok(itens);
	}
}
