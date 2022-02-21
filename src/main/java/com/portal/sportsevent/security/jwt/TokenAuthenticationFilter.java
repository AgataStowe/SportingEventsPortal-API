package com.portal.sportsevent.security.jwt;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.portal.sportsevent.model.User;
import com.portal.sportsevent.security.service.TokenFilterService;
import com.portal.sportsevent.service.UserService;

public class TokenAuthenticationFilter extends OncePerRequestFilter{
	
	@Autowired
	private TokenFilterService tokenService;
	
	@Autowired
	private UserService userService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String tokenFromHeader = getTokenFromHeader(request);
		if(tokenFromHeader != null) {
			boolean tokenValid = tokenService.isTokenValid(tokenFromHeader);
			if(tokenValid) {
				this.authenticate(tokenFromHeader, request);
			}
		}
		
		filterChain.doFilter(request, response);	
	}
	
	private void authenticate(String tokenFromHeader, HttpServletRequest request) {
		Integer id = tokenService.getTokenId(tokenFromHeader);
		
		Optional<User> optionalUser = userService.findById(Long.valueOf(id));
		
		if(optionalUser.isPresent()) {
			
			User user = optionalUser.get();
			
//			request.setAttribute("user", user);
			
			UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(user, null, null);
			SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
		}
	}

	private String getTokenFromHeader(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		if(token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
			return null;
		}
		
		return token.substring(7, token.length());
	}
}
