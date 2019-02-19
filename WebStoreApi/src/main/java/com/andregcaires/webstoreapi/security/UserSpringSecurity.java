package com.andregcaires.webstoreapi.security;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.andregcaires.webstoreapi.domain.Cliente;
import com.andregcaires.webstoreapi.domain.enums.Perfil;

/*
 * Classe que contém informações do usuário, extendendo interface do Spring Security
 * - possui métodos para validação do usuário
 *  
 * */
public class UserSpringSecurity implements UserDetails {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private String email;
	
	private String senha;
	
	private Collection<? extends GrantedAuthority> authorities; // lista de perfis
	
	
	
	
	
	public UserSpringSecurity() {
		super();
	}
	
	public UserSpringSecurity(Cliente cliente) {
		super();
		this.id = cliente.getId();
		this.email = cliente.getEmail();
		this.senha = cliente.getSenha();
		this.authorities = cliente.getPerfil().stream().map(perfil -> new SimpleGrantedAuthority(perfil.getDescricao())).collect(Collectors.toList());
	}

	public UserSpringSecurity(Integer id, String email, String senha, Set<Perfil> perfis) {
		super();
		this.id = id;
		this.email = email;
		this.senha = senha;
		this.authorities = perfis.stream().map(perfil -> new SimpleGrantedAuthority(perfil.getDescricao())).collect(Collectors.toList());
	}

	public Integer getId() {
		return id;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return senha;
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {

		return true;
	}

	@Override
	public boolean isAccountNonLocked() {

		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {

		return true;
	}

	@Override
	public boolean isEnabled() {

		return true;
	}



}
