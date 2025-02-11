package com.senai.ecommerce.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.senai.ecommerce.dto.ProdutoDTO;
import com.senai.ecommerce.entities.Produto;
import com.senai.ecommerce.repositories.ProdutoRepository;
import com.senai.ecommerce.repositories.ProdutoRespositorySpecific;

@Service
public class ProdutoService {

	@Autowired
	ProdutoRepository repo;
	@Autowired
	ProdutoRespositorySpecific repoSpecific;
	
	public List<ProdutoDTO> buscarTodos() {
		List<Produto> list = repo.findAll();
		return list.stream().map(x -> new ProdutoDTO(x)).toList();
	}
	
	public Page<ProdutoDTO> buscarPagina(Pageable pagina){
		Page<Produto> result = repo.findAll(pagina);
		return result.map(x -> new ProdutoDTO(x));
	}
	
	public ResponseEntity<?> PostProductService(String nome,String descricao,Double preco,String imgUrl) throws Exception{
		return repoSpecific.PostProduct(nome, descricao, preco, imgUrl);
	}
	
}
