package com.portal.sportsevent.security.api.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TokenDto {

	private String type;
	private String token;
}
