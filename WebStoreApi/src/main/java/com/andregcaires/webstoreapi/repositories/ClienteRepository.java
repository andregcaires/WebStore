package com.andregcaires.webstoreapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.andregcaires.webstoreapi.domain.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer>{

}
