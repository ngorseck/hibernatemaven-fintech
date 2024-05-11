package com.samanecorp.fintech.service;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.samanecorp.fintech.dto.UserDTO;


public class LoginServiceTest {

	private LoginService loginService;
	
	@BeforeEach
	void init() {
		loginService = new LoginService();
	}
	
	@Test
	void logOK () {
		
		String email = "contact@samanecorporation.com";
		String password = "passer123@";
		
		Optional<UserDTO> user = loginService.log(email, password);
		
		Assertions.assertTrue(user.isPresent());
	}
	
	@Test
	void loginOK () {
		
		UserDTO userDto = new UserDTO();
		userDto.setEmail("contact@samanecorporation.com");
		userDto.setPassword("passer123@");
		
		Optional<UserDTO> user = loginService.login(userDto.getEmail(), userDto.getPassword());
		
		Assertions.assertTrue(user.isPresent());
	}
	
	@Test
	void loginKO () {
		
		UserDTO userDto = new UserDTO();
		userDto.setEmail("seck@samanecorporation.com");
		userDto.setPassword("passer123@");
		
		Optional<UserDTO> user = loginService.login(userDto.getEmail(), userDto.getPassword());
		
		Assertions.assertTrue(user.isEmpty());
	}
}
