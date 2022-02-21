package com.portal.sportsevent.api.dto;

import lombok.Data;

@Data
public class UserRequestDto {
	
	private String fullName;
	private String nickname;
	private String email;
	private String password;

}
