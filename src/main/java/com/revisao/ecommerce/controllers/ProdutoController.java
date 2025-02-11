package com.revisao.ecommerce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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


    @PostMapping(value = "teste")
    public ResponseEntity<ProdutoDTO> criarProduto(@RequestBody ProdutoDTO produtoDTO) {
        ProdutoDTO produtoCriado = service.criarProduto(produtoDTO);
        return ResponseEntity.status(201).body(produtoCriado);
    }
}
