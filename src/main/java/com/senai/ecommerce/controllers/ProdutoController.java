package com.senai.ecommerce.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.senai.ecommerce.dto.ProdutoDTO;
import com.senai.ecommerce.services.ProdutoService;

@RestController
@RequestMapping(value = "/produto")
public class ProdutoController {

	@Autowired 
	ProdutoService service;
	
	
	@GetMapping
	public ResponseEntity<List<ProdutoDTO>> buscar(){
		return ResponseEntity.ok(service.buscarTodos());
	}
	@GetMapping(value = "pagina")
	public ResponseEntity<Page<ProdutoDTO>> buscarPagina(Pageable pagina){
		return ResponseEntity.ok(service.buscarPagina(pagina));
	}
	@PostMapping
	public ResponseEntity<ProdutoDTO> criar(@RequestBody ProdutoDTO produtoDTO){
		ProdutoDTO novoProduto = service.criar(produtoDTO); // Chama o serviço para salvar o novo produto
		return ResponseEntity.status(201).body(novoProduto); // Retorna o produto criado com status 201 (Created)
	}
}
