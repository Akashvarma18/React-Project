package com.zettamine.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zettamine.Repository.RolesRepo;
import com.zettamine.dto.RoleDto;
import com.zettamine.entity.Roles;
import com.zettamine.exception.ResourceNotFoundException;
import com.zettamine.mapper.RoleMapper;

@Service
public class RoleServiceImpl implements IRoleService {

	@Autowired
	private RolesRepo rolesRepo;
	
	@Override
	public void saveRole(RoleDto roleDto) {
		Roles role = RoleMapper.dtoToEntity(new Roles(), roleDto);
		rolesRepo.save(role);
		
	}

	@Override
	public Roles getByRoleId(Integer roleId) {
		
		Roles role = rolesRepo.findById(roleId).orElseThrow(() -> new ResourceNotFoundException("Role", "role Id", roleId.toString()));
		return role;
	}
	
	

}
