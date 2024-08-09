package com.example.swimming_ticket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = { "com.example.swimming_ticket" })
public class SwimmingTicketApplication {

	public static void main(String[] args) {
		SpringApplication.run(SwimmingTicketApplication.class, args);
	}

}
