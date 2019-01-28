package com.andregcaires.webstoreapi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andregcaires.webstoreapi.domain.Cliente;
import com.andregcaires.webstoreapi.repositories.ClienteRepository;
import com.andregcaires.webstoreapi.services.exceptions.ObjectNotFoundException;


@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository _repo;

	
	public Cliente findById(Integer id) {
		Optional<Cliente> obj = _repo.findById(id);
		
		return obj.orElseThrow( () -> {
			throw new ObjectNotFoundException("Objeto não encontrado! ID: "+id
					+" Tipo: "+ Cliente.class);
		});
	}
	
	public List<Cliente> findAll() {
		
		return _repo.findAll();
	}
	
	
}
