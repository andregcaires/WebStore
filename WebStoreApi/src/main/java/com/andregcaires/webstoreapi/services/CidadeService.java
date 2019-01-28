package com.andregcaires.webstoreapi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andregcaires.webstoreapi.domain.Categoria;
import com.andregcaires.webstoreapi.domain.Cidade;
import com.andregcaires.webstoreapi.repositories.CidadeRepository;
import com.andregcaires.webstoreapi.services.exceptions.ObjectNotFoundException;

@Service
public class CidadeService {

	@Autowired
	private CidadeRepository repo;
	
	public Cidade find(Integer id) throws Throwable {
		Optional<Cidade> obj = repo.findById(id);
		return obj.orElseThrow(() -> {throw new ObjectNotFoundException("Objeto não encontrado! ID: "+id
				+" Tipo: "+ Categoria.class);
		});
	}
	
	public List<Cidade> findAll() {
		return repo.findAll();
	}
}
