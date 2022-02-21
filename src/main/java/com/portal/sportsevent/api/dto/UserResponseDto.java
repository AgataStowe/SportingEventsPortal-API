package com.portal.sportsevent.api.dto;

import lombok.Data;

@Data
public class UserResponseDto {

	private Long id;
	private String fullName;
	private String nickname;
	private String email;
}
