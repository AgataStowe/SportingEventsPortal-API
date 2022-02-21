package com.portal.sportsevent.api.dto;

import java.sql.Timestamp;
import java.util.List;

import com.portal.sportsevent.model.Address;

import lombok.Data;

@Data
public class EventResponseDto {
	
	private Long id;
	private String description;
	private Timestamp dateTime;
	private Address address;
	private List<UserResponseDto> users;
}
