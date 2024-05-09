package com.zettamine.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zettamine.constants.AppConstants;
import com.zettamine.dto.ResponseDto;
import com.zettamine.dto.RoleDto;
import com.zettamine.service.IRoleService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/role")
public class RoleController {
	

	


	private IRoleService roleService;
		
		
		@PostMapping("/save")
		public ResponseEntity<ResponseDto> saveUser(@RequestBody RoleDto roleDto){
			roleService.saveRole(roleDto);
			return ResponseEntity 
					.status(HttpStatus.CREATED)
					.body( new ResponseDto(AppConstants.STATUS_201,AppConstants.MESSAGE_201));
		}
		


}
