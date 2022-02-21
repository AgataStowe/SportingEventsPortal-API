package com.portal.sportsevent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(info = @Info(title = "Sporting Events Portal", version = "1.0", description = "Portal for criation and dissemination of Sporting Events"))
@SpringBootApplication
public class SportsEventApplication {

	public static void main(String[] args) {
		SpringApplication.run(SportsEventApplication.class, args);
	}

}
