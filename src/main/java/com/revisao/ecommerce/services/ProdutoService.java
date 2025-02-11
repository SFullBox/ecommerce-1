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
	
	public List<ProdutoDTO> buscarTodos() {
	List<Produto> list = repo.findAll();
		return list.stream().map(x -> new ProdutoDTO(x)).toList();
	}
	public Page<ProdutoDTO> buscarPagina(Pageable  pagina){
		Page<Produto> result = repo.findAll(pagina);
		return result.map(x -> new ProdutoDTO(x));
	}
}