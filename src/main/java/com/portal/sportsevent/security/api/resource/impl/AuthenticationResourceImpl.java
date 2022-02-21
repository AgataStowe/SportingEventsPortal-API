package com.portal.sportsevent.security.api.resource.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.portal.sportsevent.security.api.dto.LoginDto;
import com.portal.sportsevent.security.api.dto.TokenDto;
import com.portal.sportsevent.security.api.resource.AuthenticationResource;
import com.portal.sportsevent.security.service.TokenFilterService;

@RestController
@RequestMapping("/auth")
public class AuthenticationResourceImpl implements AuthenticationResource{

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private TokenFilterService tokenService;
	
	@PostMapping
	public ResponseEntity<TokenDto> auth(@RequestBody @Validated LoginDto loginDto) {
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(loginDto.getUser(), loginDto.getPass());
		
		Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

		String token = tokenService.generateToken(authentication);

		return ResponseEntity.ok(TokenDto.builder().type("Bearer").token(token).build());
	}

}
