package com.portal.sportsevent.api.resource;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.portal.sportsevent.api.dto.UserRequestDto;
import com.portal.sportsevent.api.dto.UserResponseDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "User")
public interface UserResource {

	@Operation(
		summary = "Save user",
		description = " Save user int the portal",
		parameters = {
				@Parameter(
						name = "user",
						required = true,
						description = "User to be saved")})
	ResponseEntity<?> save(UserRequestDto user);
	
	@Operation(
			summary = "Update user",
			description = " Update user int the portal",
			parameters = {
					@Parameter(
							name = "id",
							required = true,
							description = "User id"),
					@Parameter(
							name = "user",
							required = true,
							description = "User to be updated")})
	ResponseEntity<UserResponseDto> update(Long id, UserRequestDto event);
	
	@Operation(
			summary = "List users",
			description = "List all of users in the portal")
	List<UserResponseDto> find();
	
	@Operation(
			summary = "Find user by id",
			description = "Find user by id in the portal",
			parameters = {
					@Parameter(
							name = "id",
							required = true,
							description = "User id")})
	ResponseEntity<UserResponseDto> findById(Long id);
	
	@Operation(
			summary = "Remove user by id",
			description = "Remove user by id in the portal",
			parameters = {
					@Parameter(
							name = "id",
							required = true,
							description = "User id")})
	ResponseEntity<Void> remove(Long id);
}
