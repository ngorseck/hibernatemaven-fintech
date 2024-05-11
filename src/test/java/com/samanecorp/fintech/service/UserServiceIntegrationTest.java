package com.samanecorp.fintech.service;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import com.samanecorp.fintech.dao.LoginDao;
import com.samanecorp.fintech.dto.UserDTO;
import com.samanecorp.fintech.entities.UserEntity;

public class UserServiceIntegrationTest {

	
	private LoginService loginService;
	
	@BeforeEach
	void init() {
		loginService = new LoginService();
	}
	
	@Test
	@Disabled
	void saveOK () {

		UserDTO userDto = new UserDTO();
		userDto.setEmail("contact@samanecorporation.com");
		userDto.setPassword("passer123@");
		
		boolean result = loginService.save(userDto);
		
		Assertions.assertTrue(result);
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

		
		String email = "contact@samanecorporation.com";
		String password = "passer123@";
		
		Optional<UserDTO> user = loginService.loginMockito(email, password);
		
		Assertions.assertTrue(user.isPresent());
	}
	
}
