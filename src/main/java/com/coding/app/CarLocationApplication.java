package com.coding.app;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.coding.app.services.UserService;

import lombok.RequiredArgsConstructor;

@SpringBootApplication
@RequiredArgsConstructor
public class CarLocationApplication implements CommandLineRunner{

	private final UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(CarLocationApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		if(!userService.adminExists()) {
			userService.createAdmin();
		}
	}
}