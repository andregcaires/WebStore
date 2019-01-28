package com.andregcaires.webstoreapi.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.andregcaires.webstoreapi.domain.Cidade;
import com.andregcaires.webstoreapi.services.CidadeService;

@RestController
@RequestMapping("/cidades")
public class CidadeResource {

	@Autowired
	private CidadeService service;
	
	@RequestMapping("/")
	public ResponseEntity<?> findAll() {
		List<Cidade> obj = service.findAll();
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping("/{id}")
	public ResponseEntity<?> find(@PathVariable Integer id) throws Throwable {
		Cidade obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}
	
	
}
