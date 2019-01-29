package com.andregcaires.webstoreapi.domain;

import java.util.Date;

import javax.persistence.Entity;

@Entity
public class PagamentoComBoleto extends Pagamento {

	private static final long serialVersionUID = 1L;

	private Date dataPagamento;
	
	private Date dataVencimento;

	public PagamentoComBoleto(Integer id, Integer estadoPagamento, Pedido pedido, Date dataPagamento, Date dataVencimento) {
		super(id, estadoPagamento, pedido);
		this.dataPagamento = dataPagamento;
		this.dataVencimento = dataVencimento;
	}

	public PagamentoComBoleto() {}

	public Date getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	
	
}
