package com.andregcaires.webstoreapi.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.andregcaires.webstoreapi.domain.Cliente;
import com.andregcaires.webstoreapi.services.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteResource {

	@Autowired
	private ClienteService service;
	
	
	@RequestMapping(value = {"/", ""}, method = RequestMethod.GET)
	public ResponseEntity<?> findAll() {
		
		List<Cliente> body = service.findAll();
		
		return ResponseEntity.ok().body(body);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Cliente> find(@PathVariable Integer id) {
		Cliente body = service.findById(id);
		return ResponseEntity.ok().body(body);
	}
	
}
