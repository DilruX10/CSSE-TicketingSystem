/**
 * Author: IT21118340
 **/

package com.example.ticketingsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class TicketingsystemApplication {

	public static void main(String[] args) {
		
		SpringApplication.run(TicketingsystemApplication.class, args);
	}
	
	@GetMapping("/")
	public ResponseEntity<String> testServer() {
		
		return new ResponseEntity<String>("Server is running..", HttpStatus.OK);
	}
}
