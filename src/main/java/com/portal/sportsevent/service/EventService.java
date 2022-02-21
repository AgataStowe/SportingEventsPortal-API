package com.portal.sportsevent.service;

import java.util.List;
import java.util.Optional;

import com.portal.sportsevent.model.Event;

public interface EventService {

	Event save(Event event);
	
	Event update(Long id, Event event);
	
	List<Event> find();
	
	Optional<Event> findById(Long id);
	
	void remove(Long id);
	
	Event linkUser(Long id, Long eventId);
}
