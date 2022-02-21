package com.portal.sportsevent.api.resource.impl;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.portal.sportsevent.api.dto.EventResponseDto;
import com.portal.sportsevent.api.resource.EventResource;
import com.portal.sportsevent.model.Event;
import com.portal.sportsevent.service.EventService;

@RestController
@RequestMapping("/api/event/")
public class EventResourceImpl implements EventResource{

	@Autowired
	private EventService service;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@PostMapping("/save")
	public ResponseEntity<?> save(@Valid @RequestBody EventResponseDto eventDto) {
		try {
			Event event = service.save(toEvent(eventDto));
			
			return ResponseEntity
					.created(new URI("/api/event/find/" + event.getId()))
					.body(toEventDto(event));
			
		} catch (URISyntaxException uri) {
			return ResponseEntity.badRequest().body(uri.getMessage());
		}
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<EventResponseDto> update(@Valid @PathVariable Long id, @RequestBody EventResponseDto eventDto){
		Event event = service.update(id, toEvent(eventDto));
		return ResponseEntity.ok(toEventDto(event));
	}


	@GetMapping("/list")
	public List<EventResponseDto> find() {
		return toEventDto(service.find());
	}
	
	@GetMapping("/teste")
	public String finds() {
		return "Hello Word";
	}

	@GetMapping("/find/{id}")
	public ResponseEntity<EventResponseDto> findById(@PathVariable("id") Long id) {
		Optional<Event> event = service.findById(id);
		return ResponseEntity.ok(toEventDto(event.get()));
	}

	@DeleteMapping("/remove/{id}")
	public ResponseEntity<Void> remove(@PathVariable("id") Long id){
		service.remove(id);
		return ResponseEntity.noContent().build(); 
	}
	
	@PostMapping("/link/user/{userId}")
	public ResponseEntity<?> linkUser(@Valid @PathVariable Long userId, @RequestBody EventResponseDto eventDto) {
		try {
			Event event = service.linkUser(userId, eventDto.getId());
			
			return ResponseEntity
					.created(new URI("/api/event/find/" + event.getId()))
					.body(toEventDto(event));
			
		} catch (URISyntaxException uri) {
			return ResponseEntity.badRequest().body(uri.getMessage());
		}
	}

	
	private EventResponseDto toEventDto(Event event) {
		return modelMapper.map(event, EventResponseDto.class);
	}
	
	private List<EventResponseDto> toEventDto(List<Event> events) {
		return modelMapper.map(events, new TypeToken<List<EventResponseDto>>() {}.getType());
	}
	
	private Event toEvent(EventResponseDto eventDto) {
		return modelMapper.map(eventDto, Event.class);
	}
}
