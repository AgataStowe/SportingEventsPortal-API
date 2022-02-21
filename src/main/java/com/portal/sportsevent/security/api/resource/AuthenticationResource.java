package com.portal.sportsevent.security.api.resource;

import org.springframework.http.ResponseEntity;

import com.portal.sportsevent.security.api.dto.LoginDto;
import com.portal.sportsevent.security.api.dto.TokenDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Auth")
public interface AuthenticationResource {

	@Operation(
			summary = "Authenticate",
			description = "Authenticate on the portal",
			parameters = {
					@Parameter(
							name = "loginDto",
							required = true,
							description = "Login to be authenticated")})
	ResponseEntity<TokenDto> auth(LoginDto loginDto);
}
