package com.andregcaires.webstoreapi.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Pedido implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	
	private Date instante;

	private Pagamento pagamento;
	
	@OneToMany
	private Cliente cliente;
	
	private Endereco endrecoDeEntrega;
	
	
}
