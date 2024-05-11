package com.samanecorp.fintech.service;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import com.samanecorp.fintech.dao.LoginDao;
import com.samanecorp.fintech.dto.UserDTO;
import com.samanecorp.fintech.entities.UserEntity;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class LoginServiceTestMockito {

	@InjectMocks
	private LoginService loginService;
	
	private LoginDao loginDao;
	
	@BeforeEach
	void init() {
		loginService = new LoginService();
		loginDao = mock(LoginDao.class);
		loginService.setLoginDao(loginDao);
	}
	
	
	@Test
	void loginOK () {

		UserEntity userEntity = new UserEntity();
		userEntity.setId(1L);
		userEntity.setEmail("contact@samanecorporation.com");
		userEntity.setPassword("passer123@");
		
		when(loginDao.loginUser(anyString(), anyString()))
			.thenReturn(Optional.ofNullable(userEntity));
		
		UserDTO userDto = new UserDTO();
		userDto.setEmail("contact@samanecorporation.com");
		userDto.setPassword("passer123@");
		
		Optional<UserDTO> user = loginService.loginMockito(userDto.getEmail(), userDto.getPassword());
		
		Assertions.assertTrue(user.isPresent());
	}
	
	
	  @Test 
	  void loginKO () {
	  
		  Mockito.lenient().when(loginDao.loginUser(anyString(), anyString()))
		  .thenReturn(Optional.ofNullable(null));
		  
		  UserDTO userDto = new UserDTO();
		  userDto.setEmail("seck@samanecorporation.com");
		  userDto.setPassword("passer123@");
		  
		  Optional<UserDTO> user = loginService.loginMockito(userDto.getEmail(),
		  userDto.getPassword());
		  
		  Assertions.assertTrue(user.isEmpty()); 
	}
	 
}
