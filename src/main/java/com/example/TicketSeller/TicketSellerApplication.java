package com.example.TicketSeller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class TicketSellerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TicketSellerApplication.class, args);
	}


}
