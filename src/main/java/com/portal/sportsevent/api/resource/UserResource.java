package com.portal.sportsevent.api.resource;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.portal.sportsevent.api.dto.UserRequestDto;
import com.portal.sportsevent.api.dto.UserResponseDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "User")
@ApiResponses(value = {
	 	@ApiResponse(responseCode = "200", description = "Successful operation."),
        @ApiResponse(responseCode = "400", description = "Bad Request: Invalid parameters.")})
public interface UserResource {

	/**
	 * Method responsible for save a user
	 * @param user user to be saved
	 * @return ResponseEntity<?> with the body returning an event (@see User) or an error 
	 */
	@Operation(
		summary = "Save user",
		description = " Save user int the portal",
		parameters = {
				@Parameter(
						name = "user",
						required = true,
						description = "User to be saved",
						schema = @Schema(type = "Object"))})
	ResponseEntity<?> save(@RequestBody UserRequestDto user);
	
	/**
	 * Method responsible for updating a user
	 * @param id user id
	 * @param user user to be updated
	 * @return ResponseEntity<UserResponseDto> a UserResponseDto
	 */
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
							description = "User to be updated",
							schema = @Schema(type = "Object"))})
	ResponseEntity<UserResponseDto> update(@PathVariable Long id, @RequestBody UserRequestDto user);
	
	/**
	 * Method responsible for listing users
	 * @return List<UserResponseDto>  UserResponseDto list
	 */
	@Operation(
			summary = "List users",
			description = "List all of users in the portal")
	List<UserResponseDto> find();
	

	/**
	 * Method responsible for getting user by id
	 * @param id user id
	 * @return ResponseEntity<UserResponseDto> UserResponseDto list
	 */
	@Operation(
			summary = "Find user by id",
			description = "Find user by id in the portal",
			parameters = {
					@Parameter(
							name = "id",
							required = true,
							description = "User id")})
	ResponseEntity<UserResponseDto> findById(@PathVariable Long id);
	
	/**
	 * Method responsible for removing user by id
	 * @param id user id
	 * @return ResponseEntity<Void> no content
	 */
	@Operation(
			summary = "Remove user by id",
			description = "Remove user by id in the portal",
			parameters = {
					@Parameter(
							name = "id",
							required = true,
							description = "User id")})
	ResponseEntity<Void> remove(@PathVariable Long id);
}
