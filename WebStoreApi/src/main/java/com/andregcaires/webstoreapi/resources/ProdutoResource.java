package com.andregcaires.webstoreapi.resources;


import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

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

import com.andregcaires.webstoreapi.domain.Produto;
import com.andregcaires.webstoreapi.dto.ProdutoDTO;
import com.andregcaires.webstoreapi.resources.utils.URL;
import com.andregcaires.webstoreapi.services.ProdutoService;

@RestController
@RequestMapping("/produtos")
public class ProdutoResource {
	
	@Autowired
	private ProdutoService _service;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {
		
		Produto body = _service.find(id);
		
		return ResponseEntity.ok().body(body);
	}
	
	@RequestMapping(value = {"/", ""}, method= RequestMethod.GET)
	public ResponseEntity<List<Produto>> findAll() {

		List<Produto> body = _service.findAll();


		return ResponseEntity.ok().body(body);
	}
	
	@RequestMapping(value = {"/", ""}, method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody Produto obj) {
		Produto body = _service.insert(obj);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(body.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping( value = {"/{id}", "/{id}/"}, method = RequestMethod.PUT )
	public ResponseEntity<Void> update(@Valid @RequestBody Produto obj, @PathVariable Integer id) {
		
		if(obj.getId() != id) {
			obj.setId(id);			
		}
		
		Produto body = _service.update(obj);
		
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping( value = {"/{id}"}, method = RequestMethod.DELETE )
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		
		_service.delete(id);
		return ResponseEntity.noContent().build();
		
	}
	
	@RequestMapping( value = {"/search"}, method = RequestMethod.GET )
	public ResponseEntity<Page<ProdutoDTO>> search(
			@RequestParam(value = "nome", defaultValue = "") String nome
			, @RequestParam(value = "categorias", defaultValue = "0") String categorias
			, @RequestParam(value = "pageIndex", defaultValue = "0") Integer pageIndex
			, @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage
			, @RequestParam(value = "orderBy", defaultValue = "nome") String orderBy
			, @RequestParam(value = "direction", defaultValue = "ASC") String direction ) {
		
		
		
		String nomeDecoded = URL.decodeParam(nome);
		
		List<Integer> categoriasInteger = URL.decodeIntList(categorias);
		
		Page<Produto> page = _service.search(nomeDecoded, categoriasInteger, pageIndex, linesPerPage, orderBy, direction);

		Page<ProdutoDTO> listDto = page.map(obj -> new ProdutoDTO(obj));
		return ResponseEntity.ok().body(listDto);
	}
	
}
