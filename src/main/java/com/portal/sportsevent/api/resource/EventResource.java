package com.portal.sportsevent.api.resource;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.portal.sportsevent.api.dto.EventResponseDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Event")
public interface EventResource {

	@Operation(
			summary = "Save event",
			description = " Save event in the portal",
			parameters = {
					@Parameter(
							name = "event",
							required = true,
							description = "Event to be saved")})
	ResponseEntity<?> save(EventResponseDto event);
	
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
							description = "Event to be updated")})
	ResponseEntity<EventResponseDto> update(Long id, EventResponseDto event);
	
	@Operation(
			summary = "List events",
			description = "List all of events int the portal")
	List<EventResponseDto> find();
	
	@Operation(
			summary = "Find by id",
			description = "Find event by id",
			parameters = {
					@Parameter(
							name = "id",
							required = true,
							description = "Event id")})
	ResponseEntity<EventResponseDto> findById(Long id);
	
	@Operation(
			summary = "Remove event by id",
			description = "Remove event by id in the portal",
			parameters = {
					@Parameter(
							name = "id",
							required = true,
							description = "Event id")})
	ResponseEntity<Void> remove(Long id);
	
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
							description = "Event id to be updated")})
	ResponseEntity<?> linkUser(Long userId, EventResponseDto eventDto);
}
