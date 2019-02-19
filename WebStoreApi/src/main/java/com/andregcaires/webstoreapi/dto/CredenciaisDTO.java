package com.andregcaires.webstoreapi.dto;

import java.io.Serializable;

import com.andregcaires.webstoreapi.domain.Cliente;

public class CredenciaisDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String email;

	private String senha;

	public CredenciaisDTO(Cliente cliente) {
		super();
		this.email = cliente.getEmail();
		this.senha = cliente.getSenha();
	}

	public CredenciaisDTO() {

	}

	public CredenciaisDTO(String email, String senha) {
		super();
		this.email = email;
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
