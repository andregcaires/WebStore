package com.andregcaires.webstoreapi.domain.enums;

public enum TipoCliente {

	PESSOAFISICA(1, "Pessoa Física"),
	PESSOAJURIDICA(2, "Pessoa Jurídica");
	
	private int cod;
	private String descricao;
	
	
	// Converte código do tipo cliente em enum
	public static TipoCliente toEnum(Integer cod) {
		
		if(cod == null) {
			return null;
		}
		for( TipoCliente x : TipoCliente.values() ) {
			if( cod.equals(x.getCod()) ) {
				return x;
			}
		}
		throw new IllegalArgumentException("Id inválido: "+ cod);
	}
	
	private TipoCliente(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public int getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}
	
}
