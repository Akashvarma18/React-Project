package com.zettamine.service;

import org.springframework.stereotype.Service;

import com.zettamine.dto.RoleDto;
import com.zettamine.email.EmailService;
import com.zettamine.entity.Roles;

import lombok.AllArgsConstructor;

public interface IRoleService{
	
	void saveRole(RoleDto roleDto);
	
	Roles getByRoleId(Integer roleId);		
}
