package com.portal.sportsevent.service;

import java.util.List;
import java.util.Optional;

import com.portal.sportsevent.model.User;

public interface UserService {

	User save(User user);
	
	User update(Long id, User user);
	
	List<User> find();
	
	Optional<User> findById(Long id);
	
	void remove(Long id);
}
