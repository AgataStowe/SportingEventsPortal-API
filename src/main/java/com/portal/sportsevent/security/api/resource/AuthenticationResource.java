package com.portal.sportsevent.security.api.resource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import com.portal.sportsevent.security.api.dto.LoginDto;
import com.portal.sportsevent.security.api.dto.TokenDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Auth")
@ApiResponses(value = {
	 	@ApiResponse(responseCode = "200", description = "Successful operation."),
        @ApiResponse(responseCode = "400", description = "Bad Request: Invalid user/password.")})
public interface AuthenticationResource {

	/**
	 * Method responsible for authenticating a user
	 * @param loginDto user to be saved
	 * @return ResponseEntity<TokenDto> a TokenDto 
	 */
	@Operation(
			summary = "Authenticate in the portal",
			description = "Authenticate on the portal",
			parameters = {
					@Parameter(
							name = "loginDto",
							required = true,
							description = "Login to be authenticated",
							schema = @Schema(type = "Object"))})
	ResponseEntity<TokenDto> auth(@RequestBody LoginDto loginDto);
}
