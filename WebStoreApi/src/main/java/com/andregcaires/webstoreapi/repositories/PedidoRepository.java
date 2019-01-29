package com.andregcaires.webstoreapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.andregcaires.webstoreapi.domain.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Integer>{

}
