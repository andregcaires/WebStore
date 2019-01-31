package com.andregcaires.webstoreapi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.andregcaires.webstoreapi.domain.Categoria;
import com.andregcaires.webstoreapi.repositories.CategoriaRepository;
import com.andregcaires.webstoreapi.services.exceptions.ConstraintException;
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
	
	public Page<Categoria> findPage(Integer pageIndex, Integer linesPerPage, String orderBy, String direction) {
		
		
		PageRequest pageRequest = PageRequest.of(pageIndex, linesPerPage, Direction.valueOf(direction), orderBy);
		
		return repo.findAll(pageRequest);
	}
	
	public Categoria insert(Categoria entity) {
		entity.setId(null);
		return repo.save(entity);
	}
	
	public Categoria update(Categoria entity) {

		find(entity.getId());
		return repo.save(entity);
	}
	
	public void delete(Integer id) {
		try {
			repo.deleteById(id);
		}catch(Exception e) {
			throw new ConstraintException("Não é possivel excluir categoria que possui produtos!");
		}
	}

}
