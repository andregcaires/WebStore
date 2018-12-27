package com.andregcaires.webstoreapi.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.andregcaires.webstoreapi.domain.Estado;
import com.andregcaires.webstoreapi.services.EstadoService;

@RestController
@RequestMapping("/estados")
public class EstadoResource {

	@Autowired
	private EstadoService service;
	
	@RequestMapping("/")
	public ResponseEntity<?> findAll() {
		
		List<Estado> obj = service.findAll();
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping("/{id}")
	public ResponseEntity<?> find(@PathVariable Integer id) {
		Estado obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}
}
