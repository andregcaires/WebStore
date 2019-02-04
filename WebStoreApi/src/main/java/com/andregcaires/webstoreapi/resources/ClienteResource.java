package com.andregcaires.webstoreapi.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.andregcaires.webstoreapi.domain.Cliente;
import com.andregcaires.webstoreapi.domain.Cliente;
import com.andregcaires.webstoreapi.dto.ClienteDTO;
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
	
	@RequestMapping(value = {"/", ""}, method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody ClienteDTO obj) {
		Cliente body = service.insert(service.fromDTO(obj));
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(body.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping( value = {"/{id}", "/{id}/"}, method = RequestMethod.PUT )
	public ResponseEntity<Void> update(@Valid @RequestBody ClienteDTO obj, @PathVariable Integer id) {
		
		if(obj.getId() != id) {
			obj.setId(id);			
		}
		
		Cliente body = service.update(service.fromDTO(obj));
		
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping( value = {"/{id}"}, method = RequestMethod.DELETE )
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		
		service.deleteById(id);
		return ResponseEntity.noContent().build();
		
	}
	
	
	@RequestMapping( value = {"/page", "/page/"}, method = RequestMethod.GET )
	public ResponseEntity<Page<ClienteDTO>> findPage( 
			@RequestParam(value = "pageIndex", defaultValue = "0") Integer pageIndex
			, @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage
			, @RequestParam(value = "orderBy", defaultValue = "nome") String orderBy
			, @RequestParam(value = "direction", defaultValue = "ASC") String direction ) {
		
		Page<ClienteDTO> body = service.findPage(pageIndex, linesPerPage, orderBy, direction).map(obj -> new ClienteDTO(obj));

		return ResponseEntity.ok().body(body);
	}
	
}
