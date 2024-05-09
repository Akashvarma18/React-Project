package com.zettamine.mapper;

import javax.management.relation.Role;

import com.zettamine.dto.RoleDto;
import com.zettamine.entity.Roles;

public class RoleMapper {
	
	public static Roles dtoToEntity(Roles entity, RoleDto dto) {
		
	     entity.setRoleId(dto.getRoleId());
	     entity.setRoleName(dto.getRoleName());
	     entity.setRoleDescription(dto.getRoleDescription());
	     
	     
	  return entity;
	}
	
	public static RoleDto entityToDto(Roles entity, RoleDto dto) {
		
		dto.setRoleId(entity.getRoleId());
		dto.setRoleName(entity.getRoleName());
		dto.setRoleDescription(entity.getRoleDescription());
		
		return dto;
	
		
	}
	

}
