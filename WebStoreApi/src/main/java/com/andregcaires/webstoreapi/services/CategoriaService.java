package com.andregcaires.webstoreapi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andregcaires.webstoreapi.domain.Categoria;
import com.andregcaires.webstoreapi.repositories.CategoriaRepository;
import com.andregcaires.webstoreapi.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repo;

	public Categoria find(Integer id) {
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElseThrow(() -> {
			throw new ObjectNotFoundException("Objeto não encontrado! ID: "+id
					+" Tipo: "+ Categoria.class);
		});
	}
	
	public List<Categoria> findAll(){
		return repo.findAll();
	}
	
	
	
	

}
