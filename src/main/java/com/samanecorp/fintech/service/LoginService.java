package com.samanecorp.fintech.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.samanecorp.fintech.controller.LoginServlet;
import com.samanecorp.fintech.dao.IUser;
import com.samanecorp.fintech.dao.LoginDao;
import com.samanecorp.fintech.dao.UserImpl;
import com.samanecorp.fintech.dto.UserDTO;
import com.samanecorp.fintech.entities.UserEntity;
import com.samanecorp.fintech.mapper.UserMapper;

public class LoginService {

	private static Logger logger = LoggerFactory.getLogger(LoginService.class);
	
	private LoginDao loginDao = new LoginDao();
	private IUser userDao = new UserImpl();
	
	public Optional<UserDTO> login (String email, String password) {
		
		if (email.equals("contact@samanecorporation.com") && password.equals("passer123@")) {
			UserDTO userDto = new UserDTO();
			userDto.setEmail(email);
			userDto.setPassword(password);
			
			return Optional.of(userDto) ;
		} else {
			return Optional.ofNullable(null);
		}
		
	}
	public Optional<UserDTO> loginMockito (String email, String password) {
		
		logger.info("Tentattive email : {} pwd : {}", email, password);
		
		Optional<UserEntity> userEntityOption = loginDao.loginUser(email, password);
		if (userEntityOption.isPresent()) {
			UserEntity userEntity = userEntityOption.get();
			UserDTO userDto = UserMapper.userEntityToUserDto(userEntity);
						
			return Optional.of(userDto) ;
		} else {
			return Optional.ofNullable(null);
		}
		
	}
	
	public Optional<UserDTO> log (String email, String password) {
		
		logger.info("Tentattive email : {} pwd : {}", email, password);
		
		Optional<UserEntity> userEntityOption = loginDao.log(email, password);
		if (userEntityOption.isPresent()) {
			UserEntity userEntity = userEntityOption.get();
			UserDTO userDto = UserMapper.userEntityToUserDto(userEntity);
						
			return Optional.of(userDto) ;
		} else {
			return Optional.ofNullable(null);
		}
		
	}
	
	public boolean save (UserDTO userDTO) {
		
		return userDao.add(UserMapper.userDtoToUserEntity(userDTO));
	}
	
	public LoginDao getLoginDao() {
		return loginDao;
	}
	public void setLoginDao(LoginDao loginDao) {
		this.loginDao = loginDao;
	}
	
	
}
