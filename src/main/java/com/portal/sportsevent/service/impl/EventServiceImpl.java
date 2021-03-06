package com.portal.sportsevent.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portal.sportsevent.exception.ResourceNotFound;
import com.portal.sportsevent.model.Event;
import com.portal.sportsevent.model.User;
import com.portal.sportsevent.repository.EventRepository;
import com.portal.sportsevent.service.EventService;
import com.portal.sportsevent.service.UserService;

@Service
public class EventServiceImpl implements EventService{
	
	@Autowired
	private EventRepository eventRepository;
	
	@Autowired
	private UserService userService;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Event save(Event event) {
		return eventRepository.save(event);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Event update(Long id, Event event){
		
		if(!eventRepository.existsById(id)) 
			throw new ResourceNotFound("Event not found");
		
		event.setId(id);
		
		return eventRepository.save(event);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Event> find() {
		return eventRepository.findActiveEvents();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Optional<Event> findById(Long id) {
		Optional<Event> event = eventRepository.findById(id);
		
		if(!event.isPresent()) 
			throw new ResourceNotFound("Event not found");
		
		return event;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void remove(Long id){
		Optional<Event> event = findById(id);
		
		event.get().setActive(false);
		eventRepository.save(event.get());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Event linkUser(Long idUser, Long eventId) {
		Optional<User> user = userService.findById(idUser);
		
		Optional<Event> event = eventRepository.findById(eventId);

		
		if(!event.isPresent())
			throw new ResourceNotFound("Event not found");

		List<User> users = new ArrayList<>();
		users.add(user.get());
		
		event.get().setUsers(users);
		
		return eventRepository.save(event.get());
	}
}
