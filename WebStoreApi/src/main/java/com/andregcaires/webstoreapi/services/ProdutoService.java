package com.andregcaires.webstoreapi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andregcaires.webstoreapi.domain.Produto;
import com.andregcaires.webstoreapi.repositories.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository repo;

	public List<Produto> findAll() {
		return repo.findAll();
	}

	public Produto find(Integer id) {
		Optional<Produto> obj = repo.findById(id);
		return obj.orElse(null);
	}
}
