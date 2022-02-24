package com.portal.sportsevent.security.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.portal.sportsevent.model.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class TokenFilterService {

	@Value("${jwt.expiration}")
	private String expiration;

	@Value("${jwt.secret}")
	private String jwtSecret;

	/**
	 * Method responsible for generate token for authentication
	 * @param authentication the data for authentication
	 * @return String a token
	 */
	public String generateToken(Authentication authentication) {

		User usuario = (User) authentication.getPrincipal();

		Date now = new Date();
		Date exp = new Date(now.getTime() + Long.parseLong(expiration));

		return Jwts.builder()
				.setSubject(usuario.getId().toString())
				.setIssuedAt(new Date())
				.setExpiration(exp)
				.signWith(SignatureAlgorithm.HS256, jwtSecret)
				.compact();
	}

	/**
	 * Method responsible for validate token
	 * @param token the token
	 * @return boolean true if valid false otherwise
	 */
	public boolean isTokenValid(String token) {
		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Method responsible for getting id token
	 * @param token the token
	 * @return Integer id token
	 */
	public Integer getTokenId(String token) {
		Claims body = Jwts.parser()
				.setSigningKey(jwtSecret)
				.parseClaimsJws(token)
				.getBody();
		
		return Integer.valueOf(body.getSubject());
	}
}
