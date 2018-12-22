package com.andregcaires.webstoreapi.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.andregcaires.webstoreapi.domain.Produto;
import com.andregcaires.webstoreapi.services.ProdutoService;

@RestController
@RequestMapping("/produtos")
public class ProdutoResource {

	@Autowired
	private ProdutoService service;

	@RequestMapping("/")
	public ResponseEntity<?> findAll() {
		List<Produto> obj = service.findAll();
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping("/{id}")
	public ResponseEntity<?> find(@PathVariable Integer id){
		Produto obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}
}
