package com.example.bootstrap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BootstrapApplication {

	public static void main(String[] args) {
		DevelopmentOnlyCustomerService customerService = new DevelopmentOnlyCustomerService();
		Demo.workWithCustomerService(BootstrapApplication.class, customerService);
	}

}
