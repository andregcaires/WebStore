package com.andregcaires.webstoreapi.security;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.andregcaires.webstoreapi.dto.CredenciaisDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

/*
 * Classe usada para interceptar a requisição e realizar filtro validando usuário
 * */
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private AuthenticationManager authenticationManager;
	
	private JWTUtil jwtUtil;

	public JWTAuthenticationFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil) {
		this.authenticationManager = authenticationManager;
		this.jwtUtil = jwtUtil;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request,
												HttpServletResponse response) throws AuthenticationException {
		
		try {
			CredenciaisDTO creds = new ObjectMapper().readValue(request.getInputStream(), CredenciaisDTO.class);
			
			
			UsernamePasswordAuthenticationToken authToken = 
					new UsernamePasswordAuthenticationToken(creds.getEmail(), creds.getSenha(), new ArrayList<>());
			
			Authentication auth = authenticationManager.authenticate(authToken);
			
			return auth;
			
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	@Override
	protected void successfulAuthentication (HttpServletRequest request,
			HttpServletResponse response,
			FilterChain chain, 
			Authentication auth) {
		
		String username = ((UserSpringSecurity) auth.getPrincipal()).getUsername();
		String token = jwtUtil.generateToken(username);
		
		response.addHeader("Authorization", "Bearer "+token);
		
	}
	
}
