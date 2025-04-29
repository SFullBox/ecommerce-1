package com.senai.ecommerce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.senai.ecommerce.dto.CarrinhoDTO;
import com.senai.ecommerce.services.CarrinhoService;

@RestController
@RequestMapping(value = "/carrinho")
public class CarrinhoController {

    @Autowired
    private CarrinhoService carrinhoService;

    @GetMapping
    public ResponseEntity<CarrinhoDTO> buscarCarrinho() {
        CarrinhoDTO carrinho = carrinhoService.buscarCarrinho();
        return ResponseEntity.ok(carrinho);
    }

    @PostMapping("/adicionar")
    public ResponseEntity<CarrinhoDTO> adicionarItem(@RequestBody CarrinhoDTO dto) {
        CarrinhoDTO carrinho = carrinhoService.adicionarItem(dto);
        return ResponseEntity.ok(carrinho);
    }

    @DeleteMapping("/remover/{produtoId}")
    public ResponseEntity<CarrinhoDTO> removerItem(@PathVariable Long produtoId) {
        CarrinhoDTO carrinho = carrinhoService.removerItem(produtoId);
        return ResponseEntity.ok(carrinho);
    }
} 