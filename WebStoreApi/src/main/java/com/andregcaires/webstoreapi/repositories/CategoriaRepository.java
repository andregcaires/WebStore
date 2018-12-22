package com.andregcaires.webstoreapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.andregcaires.webstoreapi.domain.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer>  {

}
