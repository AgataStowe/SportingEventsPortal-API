package com.portal.sportsevent.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.portal.sportsevent.exception.InformationAlreadyExists;
import com.portal.sportsevent.exception.ResourceNotFound;
import com.portal.sportsevent.model.User;
import com.portal.sportsevent.repository.UserRepository;
import com.portal.sportsevent.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public User save(User user) {
		validUser(user);
		
		BCryptPasswordEncoder criptografar = new BCryptPasswordEncoder();
		
		user.setPassword(criptografar.encode(user.getPassword()));
		
		return userRepository.save(user);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public User update(Long id, User user) {
		
		if(!userRepository.existsById(id)) throw new ResourceNotFound("User not found");
		
		user.setId(id);
		
		validUser(user);
		
		return userRepository.save(user);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<User> find() {
		return userRepository.findActiveUsers();
	}

	@Override
	public Optional<User> findById(Long id) {
		Optional<User> user = userRepository.findById(id);
		
		if(!user.isPresent()) throw new ResourceNotFound("User not found");

		return user;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void remove(Long id) {
		
		Optional<User> user = findById(id);

		user.get().setActive(false);
		userRepository.save(user.get());
	}
	
	private void validUser(User user) {
		Optional<User> userSaved = userRepository.findByEmail(user.getId(), user.getEmail()); 																 
		if(userSaved.isPresent()) 
			throw new InformationAlreadyExists("Email already registered for another user");
	}

}
