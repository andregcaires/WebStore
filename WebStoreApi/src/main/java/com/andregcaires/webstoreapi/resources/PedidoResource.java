package com.andregcaires.webstoreapi.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.andregcaires.webstoreapi.domain.Pedido;
import com.andregcaires.webstoreapi.services.PedidoService;

@RestController
@RequestMapping("/pedidos")
public class PedidoResource {

	@Autowired
	private PedidoService _service;
	
	@RequestMapping(value = {"/", ""}, method = RequestMethod.GET)
	public ResponseEntity<?> findAll() {
		List<Pedido> body = _service.findAll();
		
		return ResponseEntity.ok().body(body);
	}
	
	@RequestMapping(value = {"/{id}", "/{id}/"}, method = RequestMethod.GET)
	public ResponseEntity<Pedido> find(@PathVariable Integer id) {
		Pedido body = _service.find(id);
		
		return ResponseEntity.ok().body(body);
	}
	
}
