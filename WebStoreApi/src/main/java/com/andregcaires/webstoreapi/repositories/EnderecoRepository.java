package com.andregcaires.webstoreapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.andregcaires.webstoreapi.domain.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {

}
