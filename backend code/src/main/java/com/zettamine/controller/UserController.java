package com.zettamine.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zettamine.constants.AppConstants;
import com.zettamine.dto.ResponseDto;
import com.zettamine.dto.UserDto;
import com.zettamine.service.IUserService;

import lombok.AllArgsConstructor;

@RestController
@CrossOrigin
@AllArgsConstructor
@RequestMapping("api/v1/user")
public class UserController {
	
	private IUserService userService;
	
	@PostMapping("/save")
	public ResponseEntity<ResponseDto> saveUser (@RequestBody UserDto userDto){
		
		
		userService.save(userDto);
		
		return ResponseEntity.status(HttpStatus.CREATED)
				             .body(new ResponseDto(AppConstants.STATUS_201,AppConstants.MESSAGE_201));
		
		
		
	}
	
	

}
