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

import com.portal.sportsevent.api.dto.UserRequestDto;
import com.portal.sportsevent.api.dto.UserResponseDto;
import com.portal.sportsevent.api.resource.UserResource;
import com.portal.sportsevent.model.User;
import com.portal.sportsevent.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserResourceImpl implements UserResource{
	
	@Autowired
	private UserService service;
	
	@Autowired
	private ModelMapper modelMapper;

	/**
	 * {@inheritDoc}
	 */
	@PostMapping("/save")
	@Override
	public ResponseEntity<?> save(@Valid @RequestBody UserRequestDto userDto) {
		try {
			User user = service.save(toUser(userDto));
			
			return ResponseEntity
					.created(new URI("/api/user/find/" + user.getId()))
					.body(toUserDto(user));
			
		} catch (URISyntaxException uri) {
			return ResponseEntity.badRequest().body(uri.getMessage());
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@PutMapping("/update/{id}")
	public ResponseEntity<UserResponseDto> update(@Valid @PathVariable Long id, @RequestBody UserRequestDto userDto) {
		User user = service.update(id, toUser(userDto));
		return ResponseEntity.ok(toUserDto(user));
	}

	/**
	 * {@inheritDoc}
	 */
	@GetMapping("/list")
	public List<UserResponseDto> find() {
		return  toUserDto(service.find());
	}

	/**
	 * {@inheritDoc}
	 */
	@GetMapping("/find/{id}")
	public ResponseEntity<UserResponseDto> findById(@PathVariable("id") Long id) {
		Optional<User> user = service.findById(id);
		return ResponseEntity.ok(toUserDto(user.get()));
	}

	/**
	 * {@inheritDoc}
	 */
	@DeleteMapping("/remove/{id}")
	public ResponseEntity<Void> remove(@PathVariable("id") Long id) {
		service.remove(id);
		return ResponseEntity.noContent().build();
	}

	private UserResponseDto toUserDto(User user) {
		return modelMapper.map(user, UserResponseDto.class);
	}
	
	private List<UserResponseDto> toUserDto(List<User> users) {
		return modelMapper.map(users, new TypeToken<List<UserResponseDto>>() {}.getType());
	}
	
	private User toUser(UserRequestDto userDto) {
		return modelMapper.map(userDto, User.class);
	}
}
