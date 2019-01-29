package com.andregcaires.webstoreapi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andregcaires.webstoreapi.domain.Pedido;
import com.andregcaires.webstoreapi.repositories.PedidoRepository;
import com.andregcaires.webstoreapi.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository _repo;
	
	
	public List<Pedido> findAll() {
		return _repo.findAll();
	}
	
	public Pedido find(Integer id) {
		
		Optional<Pedido> obj = _repo.findById(id);
		
		return obj.orElseThrow( () -> {
			throw new ObjectNotFoundException("Objeto não encontrado: ID:"+ id + "Tipo: "+ Pedido.class.getName());
		} );
	}
	
}
