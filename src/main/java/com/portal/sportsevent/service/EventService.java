package com.portal.sportsevent.service;

import java.util.List;
import java.util.Optional;

import com.portal.sportsevent.model.Event;

public interface EventService {

	/**
	 * Method responsible for save an event
	 * @param event event to be saved
	 * @return Event 
	 */
	Event save(Event event);
	
	/**
	 * Method responsible for updating an event
	 * @param id event id
	 * @param event event to be updated
	 * @return Event
	 */
	Event update(Long id, Event event);
	
	/**
	 * Method responsible for listing events
	 * @return List<Event> Event list
	 */
	List<Event> find();
	
	/**
	 * Method responsible for getting event by id
	 * @param id event id
	 * @return Optional<Event> Event list or none
	 */
	Optional<Event> findById(Long id);
	
	/**
	 * Method responsible for removing event by id
	 * @param id event id
	 */
	void remove(Long id);
	
	/**
	 * Method responsible for linking event by id
	 * @param id user id to be linked to event
	 * @param eventId event to be linked to user
	 * @return Event
	 */
	Event linkUser(Long id, Long eventId);
}
