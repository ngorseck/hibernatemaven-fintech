package com.samanecorp.fintech.mapper;

import com.samanecorp.fintech.dto.UserDTO;
import com.samanecorp.fintech.entities.UserEntity;

public class UserMapper {
	
	public static UserEntity userDtoToUserEntity(UserDTO userDTO) {
		
		UserEntity userEntity = new UserEntity();
		userEntity.setId(userDTO.getId());
		userEntity.setEmail(userDTO.getEmail());
		userEntity.setPassword(userDTO.getPassword());
		
		return userEntity;
	}
	
	public static UserDTO userEntityToUserDto(UserEntity userEntity) {
		
		UserDTO userDto = new UserDTO();
		userDto.setId(userEntity.getId());
		userDto.setEmail(userEntity.getEmail());
		userDto.setPassword(userEntity.getPassword());
		
		return userDto;
	}

}
