package com.revisao.ecommerce.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.revisao.ecommerce.dto.ProdutoDTO;
import com.revisao.ecommerce.entities.Produto;
import com.revisao.ecommerce.repositories.ProdutoRepository;

@Service
public class ProdutoService {

    @Autowired 
    ProdutoRepository repo;
    
    public Page<ProdutoDTO> buscarTodos() {
        Page<Produto> list = (Page<Produto>) repo.findAll();
        return (Page<ProdutoDTO>) list.stream().map(x -> new ProdutoDTO(x)).toList();
    }

    public Page<ProdutoDTO> buscarPagina(Pageable pagina){
        Page<Produto> result = repo.findAll(pagina);
        return result.map(x -> new ProdutoDTO(x));
    }

    
    public ProdutoDTO criarProduto(ProdutoDTO produtoDTO) {
        Produto produto = new Produto();
        produto.setNome(produtoDTO.getNome());
        produto.setDescricao(produtoDTO.getDescricao());
        produto.setPreco(produtoDTO.getPreco());
        produto.setImgUrl(produtoDTO.getImgUrl());

        Produto produtoSalvo = repo.save(produto);

        return new ProdutoDTO(produtoSalvo);
    }
}
