package com.puc.ticketin;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(
		title = "Tiketin", description = "Documents"
))
public class TicketinApplication {

	public static void main(String[] args) {
		SpringApplication.run(TicketinApplication.class, args);
	}

}
