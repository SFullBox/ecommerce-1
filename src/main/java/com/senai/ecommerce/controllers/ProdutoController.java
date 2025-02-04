package com.senai.ecommerce.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.senai.ecommerce.entities.Produto;
import com.senai.ecommerce.services.ProdutoService;

@RestController
@RequestMapping(value = "/produto")
public class ProdutoController {
	
	
	@Autowired
	ProdutoService repo;
	
	@GetMapping
	public ResponseEntity<?> buscar(){
		List<Produto> list = new ArrayList<Produto>();
		
		return ResponseEntity.ok(repo.buscarTodos());
	}
}
