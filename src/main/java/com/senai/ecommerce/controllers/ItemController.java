package com.senai.ecommerce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.senai.ecommerce.dto.ItemDTO;
import com.senai.ecommerce.services.ItemService;

@RestController
@RequestMapping(value = "item")
public class ItemController {
	
	@Autowired
	ItemService itemService;
	
	@PostMapping
	public ResponseEntity<ItemDTO> inserir(@RequestBody ItemDTO itemDTO){
		itemDTO = itemService.teste(itemDTO);
		return ResponseEntity.ok(itemDTO);
	}

}
