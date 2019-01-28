package com.andregcaires.webstoreapi.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.andregcaires.webstoreapi.domain.Endereco;
import com.andregcaires.webstoreapi.services.EnderecoService;

@RestController
@RequestMapping("/enderecos")
public class EnderecoResource {

	@Autowired
	private EnderecoService _service;
	
	@RequestMapping(value= {"/", ""}, method = RequestMethod.GET)
	public ResponseEntity<?> findAll() {
		
		List<Endereco> body = _service.findAll();
		
		return ResponseEntity.ok().body(body);		
	}
	
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> findById(@PathVariable Integer id) {
		
		Endereco body = _service.findById(id);
		
		return ResponseEntity.ok().body(body);
	}
	
	
}
