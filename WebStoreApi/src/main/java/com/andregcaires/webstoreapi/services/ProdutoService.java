package com.andregcaires.webstoreapi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.andregcaires.webstoreapi.domain.Categoria;
import com.andregcaires.webstoreapi.domain.Produto;
import com.andregcaires.webstoreapi.repositories.CategoriaRepository;
import com.andregcaires.webstoreapi.repositories.ProdutoRepository;
import com.andregcaires.webstoreapi.services.exceptions.ObjectNotFoundException;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository _repo;
	
	@Autowired
	private CategoriaRepository _categoriaRepository;

	public List<Produto> findAll() {
		return _repo.findAll();
	}
	
	public Produto update(Produto obj) {
		
		find(obj.getId());
		
		return _repo.save(obj);		
	}
	
	public void delete(Integer id) {
		find(id);
		_repo.deleteById(id);
	}
	
	public Produto insert(Produto obj) {
		obj.setId(null);
		return _repo.save(obj);
	}

	public Produto find(Integer id) {
		Optional<Produto> obj = _repo.findById(id);
		return obj.orElseThrow( () -> {
			throw new ObjectNotFoundException("Objeto não encontrado: ID:"+ id + "Tipo: "+ Produto.class.getName());
		} );
	}
	
	public Page<Produto> search(String nome, List<Integer> ids, Integer pageIndex, Integer linesPerPage, String orderBy, String direction) {
		
		PageRequest pageRequest = PageRequest.of(pageIndex, linesPerPage, Direction.valueOf(direction), orderBy);
		
		List<Categoria> categorias = _categoriaRepository.findAllById(ids);
		return _repo.findDistinctByNomeContainingAndCategoriasIn(nome, categorias, pageRequest);
		// search
	}
}
