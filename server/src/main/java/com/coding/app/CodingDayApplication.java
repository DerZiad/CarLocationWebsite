package com.coding.app;

import com.coding.app.models.User;
import com.coding.app.models.enums.ServerRole;
import com.coding.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class CodingDayApplication implements CommandLineRunner{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	public static void main(String[] args) {
		SpringApplication.run(CodingDayApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		if(userRepository.findAll().size() == 0) {
			User user = new User();
			user.setVerificated(true);
			user.setAccountNonLocked(true);
			user.setPassword(passwordEncoder.encode("admin"));
			user.setUsername("smookerzz.administrator");
			user.setEmail("ziadbougrine@gmail.com");
			user.setRoles(ServerRole.ADMIN.name());
			userRepository.save(user);
		}
	}
	
}