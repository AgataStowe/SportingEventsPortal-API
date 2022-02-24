package com.portal.sportsevent.api.resource;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.portal.sportsevent.api.dto.EventResponseDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Event")
@ApiResponses(value = {
	 	@ApiResponse(responseCode = "200", description = "Successful operation."),
        @ApiResponse(responseCode = "400", description = "Bad Request: Invalid parameters.")})
public interface EventResource {

	/**
	 * Method responsible for save an event
	 * @param event event to be saved
	 * @return ResponseEntity<?> with the body returning an event (@see Event) or an error 
	 */
	@Operation(
			summary = "Save event",
			description = " Save event in the portal",
			tags = { "event" },
			parameters = {
					@Parameter(
							name = "event",
							required = true,
							description = "Event to be saved",
							schema = @Schema(type = "Object"))}
							)
	ResponseEntity<?> save(@Valid @RequestBody EventResponseDto event);
	
	/**
	 * Method responsible for updating an event
	 * @param id event id
	 * @param event event to be updated
	 * @return ResponseEntity<EventResponseDto> an EventResponseDto
	 */
	@Operation(
			summary = "Update event",
			description = " Update event int the portal",
			parameters = {
					@Parameter(
							name = "id",
							required = true,
							description = "Event id"),
					@Parameter(
							name = "event",
							required = true,
							description = "Event to be updated",
							schema = @Schema(type = "Object"))})
	ResponseEntity<EventResponseDto> update(@PathVariable Long id, @RequestBody EventResponseDto event);
	
	/**
	 * Method responsible for listing events
	 * @return List<EventResponseDto>  EventResponseDto list
	 */
	@Operation(
			summary = "List events",
			description = "List all of events int the portal")
	List<EventResponseDto> find();
	
	/**
	 * Method responsible for getting event by id
	 * @param id event id
	 * @return ResponseEntity<EventResponseDto> EventResponseDto list
	 */
	@Operation(
			summary = "Find by id",
			description = "Find event by id",
			parameters = {
					@Parameter(
							name = "id",
							required = true,
							description = "Event id")})
	ResponseEntity<EventResponseDto> findById(@PathVariable Long id);
	
	/**
	 * Method responsible for removing event by id
	 * @param id event id
	 * @return ResponseEntity<Void> no content
	 */
	@Operation(
			summary = "Remove event by id",
			description = "Remove event by id in the portal",
			parameters = {
					@Parameter(
							name = "id",
							required = true,
							description = "Event id")})
	ResponseEntity<Void> remove(@PathVariable Long id);
	
	/**
	 * Method responsible for linking event by id
	 * @param userId user id to be linked to event
	 * @param eventDto event to be linked to user
	 * @return ResponseEntity<?> with the body returning an event (@see Event) or an error 
	 */
	@Operation(
			summary = "Link user to event",
			description = "Link user to event int the portal",
			parameters = {
					@Parameter(
							name = "userId",
							required = true,
							description = "User id to be linked"),
					@Parameter(
							name = "eventDto",
							required = true,
							description = "Event id to be updated",
							schema = @Schema(type = "Object"))})
	ResponseEntity<?> linkUser(@PathVariable Long userId, @RequestBody EventResponseDto eventDto);
}
