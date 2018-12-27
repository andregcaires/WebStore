package com.andregcaires.webstoreapi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andregcaires.webstoreapi.domain.Categoria;
import com.andregcaires.webstoreapi.domain.Estado;
import com.andregcaires.webstoreapi.repositories.EstadoRepository;
import com.andregcaires.webstoreapi.services.exceptions.ObjectNotFoundException;

@Service
public class EstadoService {

	@Autowired
	private EstadoRepository repo;
	
	public Estado find(Integer id) {
		Optional<Estado> obj = repo.findById(id);
		
		return obj.orElseThrow(() -> {
			throw new ObjectNotFoundException("Objeto não encontrado! ID: "+id
					+" Tipo: "+ Categoria.class);
		});
	}
	
	public List<Estado> findAll() {
		return repo.findAll();
	}
}
