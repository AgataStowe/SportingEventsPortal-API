package com.portal.sportsevent.security.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.portal.sportsevent.exception.ResourceNotFound;
import com.portal.sportsevent.model.User;
import com.portal.sportsevent.repository.UserRepository;

@Service
public class AuthenticationService implements UserDetailsService{

	@Autowired
	private UserRepository repository;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> optional = repository.getByEmail(username);
		
		if(optional.isPresent()) {
			return optional.get();
		}
		
		throw new ResourceNotFound("User not found");
	}

}
