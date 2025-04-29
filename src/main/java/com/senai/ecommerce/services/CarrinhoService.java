package com.senai.ecommerce.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.senai.ecommerce.dto.CarrinhoDTO;
import com.senai.ecommerce.dto.ItemCarrinhoDTO;
import com.senai.ecommerce.entities.Produto;
import com.senai.ecommerce.repositories.ProdutoRepository;

@Service
public class CarrinhoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    private List<ItemCarrinhoDTO> itens = new ArrayList<>();

    public CarrinhoDTO buscarCarrinho() {
        CarrinhoDTO carrinho = new CarrinhoDTO();
        carrinho.setItens(itens);
        return carrinho;
    }

    public CarrinhoDTO adicionarItem(CarrinhoDTO dto) {
        Optional<Produto> produtoOpt = produtoRepository.findById(dto.getProdutoId());
        
        if (produtoOpt.isPresent()) {
            Produto produto = produtoOpt.get();
            
            Optional<ItemCarrinhoDTO> itemExistente = itens.stream()
                .filter(item -> item.getProdutoId().equals(produto.getId()))
                .findFirst();

            if (itemExistente.isPresent()) {
                ItemCarrinhoDTO item = itemExistente.get();
                item.setQuantidade(item.getQuantidade() + dto.getQuantidade());
            } else {
                ItemCarrinhoDTO novoItem = new ItemCarrinhoDTO();
                novoItem.setProdutoId(produto.getId());
                novoItem.setNome(produto.getNome());
                novoItem.setPreco(produto.getPreco());
                novoItem.setQuantidade(dto.getQuantidade());
                itens.add(novoItem);
            }
        }

        return buscarCarrinho();
    }

    public CarrinhoDTO removerItem(Long produtoId) {
        itens.removeIf(item -> item.getProdutoId().equals(produtoId));
        return buscarCarrinho();
    }
} 