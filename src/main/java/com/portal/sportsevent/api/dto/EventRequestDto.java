package com.portal.sportsevent.api.dto;

import java.sql.Timestamp;
import java.util.List;

import com.portal.sportsevent.model.Address;

import lombok.Data;

@Data
public class EventRequestDto {
	
	private String description;
	private Timestamp dateTime;
	private Address address;
	private List<UserResponseDto> users;
}
