package com.andregcaires.webstoreapi.services;

import org.springframework.security.core.context.SecurityContextHolder;

import com.andregcaires.webstoreapi.security.UserSpringSecurity;

public class UserService {

	/*
	 * Método que retorna o usuário logado
	 */
	public static UserSpringSecurity authenticated() {
		try {
			return (UserSpringSecurity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (Exception e) {
			return null;
		}
	}
}
