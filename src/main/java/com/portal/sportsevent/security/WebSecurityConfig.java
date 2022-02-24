package com.portal.sportsevent.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.portal.sportsevent.security.jwt.AuthEntryPointJwt;
import com.portal.sportsevent.security.jwt.TokenAuthenticationFilter;
import com.portal.sportsevent.security.service.AuthenticationService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private AuthEntryPointJwt unauthorizedHandler;
	
	@Autowired
	private AuthenticationService authenticationService;
	
	/**
	 * Method responsible for getting an instance of TokenAuthenticationFilter
	 * @return TokenAuthenticationFilter an instance of TokenAuthenticationFilter
	 */
	@Bean
	public TokenAuthenticationFilter authenticationJwtTokenFilter() {
		return new TokenAuthenticationFilter();
	}

	/**
	 * {@inheritDoc}
	 */
	//Configurations for authentication
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    	auth.userDetailsService(authenticationService).passwordEncoder(new BCryptPasswordEncoder());
    }
	
    /**
	 * {@inheritDoc}
	 */
    //Configuration for authorization
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http
			.cors().and().csrf().disable()
			.exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and().authorizeRequests().antMatchers("/auth","/portal-docs/swagger-ui.html", "/api/user/save").permitAll()
			.antMatchers("/api/event/**", "/api/user/update/**", "/api/user/list", "/api/user/find/**",
					"/api/user/remove/**").authenticated();
			//.and().(new TokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
		
		http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
	    return super.authenticationManagerBean();
	}
}
