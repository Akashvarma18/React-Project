package com.zettamine.mapper;

import com.zettamine.dto.UserDto;
import com.zettamine.entity.User;

public class UserMapper {

	public static User dtoToEntity(User entity, UserDto dto) {

		entity.setFirstName(dto.getFirstName());
		entity.setLastName(dto.getLastName());
		entity.setEmail(dto.getEmail());
		entity.setDesignation(dto.getDesignation());

		return entity;
	}

     public static UserDto entityToDto(User entity, UserDto dto) {
		
		dto.setFirstName(entity.getFirstName());
		dto.setLastName(entity.getLastName());
		dto.setEmail(entity.getEmail());
		dto.setDesignation(entity.getDesignation());
		
		return dto;	
    }
}     
