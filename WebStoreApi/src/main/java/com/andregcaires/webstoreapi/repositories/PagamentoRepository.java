package com.andregcaires.webstoreapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.andregcaires.webstoreapi.domain.Pagamento;

public interface PagamentoRepository extends JpaRepository<Pagamento, Integer> {

}
