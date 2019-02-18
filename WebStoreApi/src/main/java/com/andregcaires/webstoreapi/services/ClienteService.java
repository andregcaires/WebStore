package com.andregcaires.webstoreapi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.andregcaires.webstoreapi.domain.Cidade;
import com.andregcaires.webstoreapi.domain.Cliente;
import com.andregcaires.webstoreapi.domain.Endereco;
import com.andregcaires.webstoreapi.domain.enums.TipoCliente;
import com.andregcaires.webstoreapi.dto.ClienteDTO;
import com.andregcaires.webstoreapi.dto.ClienteEnderecoDTO;
import com.andregcaires.webstoreapi.repositories.ClienteRepository;
import com.andregcaires.webstoreapi.repositories.EnderecoRepository;
import com.andregcaires.webstoreapi.services.exceptions.ConstraintException;
import com.andregcaires.webstoreapi.services.exceptions.ObjectNotFoundException;


@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository _repo;

	@Autowired
	private EnderecoRepository _enderecoRepo;
	
	@Autowired
	private BCryptPasswordEncoder _bcrypt;
	
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
		entity.setTipo(temp.getTipo().getCod());
		return _repo.save(entity);
	}
	
	@Transactional
	public Cliente insert(Cliente entity) {
		
		entity.setId(null);
		entity = _repo.save(entity);
		_enderecoRepo.saveAll(entity.getEnderecos());
		return entity;
	}
	
	public void deleteById(Integer id) {
		try {
			_repo.deleteById(id);
		}
		catch(DataIntegrityViolationException err) {
			throw new ConstraintException("Não é possivel excluir cliente com pedidos relacionados!");
		}
	}
	
	public Page<Cliente> findPage(Integer pageIndex, Integer linesPerPage, String orderBy, String direction) {		
		
		PageRequest pageRequest = PageRequest.of(pageIndex, linesPerPage, Direction.valueOf(direction), orderBy);
		
		return _repo.findAll(pageRequest);
	}
	
	public Cliente fromDTO(ClienteDTO dto) {
		return new Cliente(dto.getId(), dto.getNome(), dto.getEmail(), null, null, null);
	}
	
	public Cliente fromDTO(ClienteEnderecoDTO dto) {
		Cliente cli = new Cliente(null, dto.getNome()
				, dto.getEmail(), dto.getCpfOuCnpj()
				, TipoCliente.toEnum(dto.getTipo()), _bcrypt.encode(dto.getSenha()));	
		
		
		Endereco end = new Endereco(null, dto.getLogradouro(), dto.getNumero()
				, dto.getComplemento(), dto.getBairro(), dto.getCep()
				, cli, new Cidade(dto.getCidadeId(), null, null));
		
		cli.getTelefones().addAll(dto.getTelefones());
		cli.getEnderecos().add(end);
		
		return cli;
	}
	
}
