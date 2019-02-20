package com.andregcaires.webstoreapi.resources;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.andregcaires.webstoreapi.dto.EmailDTO;
import com.andregcaires.webstoreapi.security.JWTUtil;
import com.andregcaires.webstoreapi.security.UserSpringSecurity;
import com.andregcaires.webstoreapi.services.AuthService;
import com.andregcaires.webstoreapi.services.UserService;

@RestController
@RequestMapping("/auth")
public class AuthResource {

	@Autowired
	private JWTUtil jwtUtil;
	
	@Autowired
	private AuthService _service;
	
	@RequestMapping(method = RequestMethod.POST, value = {"/refresh_token"})
	public ResponseEntity<Void> auth(HttpServletResponse response) {
		
		UserSpringSecurity user = UserService.authenticated();
		
		String token = jwtUtil.generateToken(user.getUsername());
		
		response.addHeader("Authorization", "Bearer "+ token);
		
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(method = RequestMethod.POST, value = {"/forgot"})
	public ResponseEntity<Void> forgot(@Valid @RequestBody EmailDTO dto) {
		
		_service.sendNewPassword(dto.getEmail());
		return ResponseEntity.noContent().build();
	}
}
