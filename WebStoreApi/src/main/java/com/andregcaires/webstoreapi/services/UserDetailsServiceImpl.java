package com.andregcaires.webstoreapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.andregcaires.webstoreapi.domain.Cliente;
import com.andregcaires.webstoreapi.repositories.ClienteRepository;
import com.andregcaires.webstoreapi.security.UserSpringSecurity;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private ClienteRepository _repo;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		Cliente cliente = _repo.findByEmail(email);
		
		if(cliente == null) {
			throw new UsernameNotFoundException(email);
		}
		
		return new UserSpringSecurity(cliente);
	}

}
