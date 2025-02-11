package com.revisao.ecommerce.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revisao.ecommerce.dto.ProdutoDTO;
import com.revisao.ecommerce.services.ProdutoService;

@RestController
@RequestMapping(value = "/produto")
public class ProdutoController {

	@Autowired 
	ProdutoService service;
	
	
	@GetMapping(value = "pagina")
	public ResponseEntity<Page<ProdutoDTO>> buscarPagina(Pageable pagina){
		return ResponseEntity.ok(service.buscarPagina(pagina));
	}

}