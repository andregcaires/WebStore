package com.andregcaires.webstoreapi.domain;

import java.io.Serializable;

import javax.persistence.Entity;

import com.andregcaires.webstoreapi.domain.enums.EstadoPagamento;

@Entity
public class Pagamento {


	private Integer id;
	
	private Integer estadoPagamento;

	public Pagamento(Integer id, EstadoPagamento estadoPagamento) {
		super();
		this.id = id;
		this.estadoPagamento = estadoPagamento.getCod();
	}
	
	public Pagamento () {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public EstadoPagamento getEstadoPagamento() {
		return EstadoPagamento.toEnum(estadoPagamento);
	}

	public void setEstadoPagamento(EstadoPagamento estadoPagamento) {
		this.estadoPagamento = estadoPagamento.getCod();
	}
	
	
	
}
