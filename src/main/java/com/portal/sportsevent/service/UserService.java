package com.portal.sportsevent.service;

import java.util.List;
import java.util.Optional;

import com.portal.sportsevent.model.User;

public interface UserService {

	/**
	 * Method responsible for save a user
	 * @param user user to be saved
	 * @return User
	 */
	User save(User user);
	
	/**
	 * Method responsible for updating a user
	 * @param id user id
	 * @param user user to be updated
	 * @return User
	 */
	User update(Long id, User user);
	
	/**
	 * Method responsible for listing users
	 * @return List<User> User list
	 */
	List<User> find();
	
	/**
	 * Method responsible for getting user by id
	 * @param id user id
	 * @return Optional<User> a User or none
	 */
	Optional<User> findById(Long id);
	
	/**
	 * Method responsible for removing user by id
	 * @param id user id
	 */
	void remove(Long id);
}
