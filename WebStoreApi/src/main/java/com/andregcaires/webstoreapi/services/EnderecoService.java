package com.andregcaires.webstoreapi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andregcaires.webstoreapi.domain.Endereco;
import com.andregcaires.webstoreapi.repositories.EnderecoRepository;
import com.andregcaires.webstoreapi.services.exceptions.ObjectNotFoundException;

@Service
public class EnderecoService {

	@Autowired
	private EnderecoRepository _repo;
	
	
	public Endereco findById(Integer id) {
		
		Optional<Endereco> obj = _repo.findById(id);
		
		return obj.orElseThrow( () -> {
			throw new ObjectNotFoundException("Objeto não encontrado! ID: "+id
					+" Tipo: "+ Endereco.class);
		});
	}
	
	public List<Endereco> findAll() {
		return _repo.findAll();
	}
	
}
