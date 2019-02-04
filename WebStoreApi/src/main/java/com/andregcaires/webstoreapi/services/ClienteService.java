package com.andregcaires.webstoreapi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.andregcaires.webstoreapi.domain.Categoria;
import com.andregcaires.webstoreapi.domain.Cliente;
import com.andregcaires.webstoreapi.domain.enums.TipoCliente;
import com.andregcaires.webstoreapi.dto.CategoriaDTO;
import com.andregcaires.webstoreapi.dto.ClienteDTO;
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
	
	public Cliente update(Cliente entity) {
		Cliente temp = findById(entity.getId());
		entity.setCpfOuCnpj(temp.getCpfOuCnpj());
		entity.setTipo(temp.getTipo());
		return _repo.save(entity);
	}
	
	public Cliente insert(Cliente entity) {
		
		entity.setId(null);
		return _repo.save(entity);
	}
	
	public void deleteById(Integer id) {
		_repo.deleteById(id);
	}
	
	public Page<Cliente> findPage(Integer pageIndex, Integer linesPerPage, String orderBy, String direction) {		
		
		PageRequest pageRequest = PageRequest.of(pageIndex, linesPerPage, Direction.valueOf(direction), orderBy);
		
		return _repo.findAll(pageRequest);
	}
	
	public Cliente fromDTO(ClienteDTO dto) {
		return new Cliente(dto.getId(), dto.getNome(), dto.getEmail(), null, null);
	}
	
}
