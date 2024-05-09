package com.zettamine.controller;

import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zettamine.constants.AppConstants;
import com.zettamine.dto.LoginDto;
import com.zettamine.dto.ResponseDto;
import com.zettamine.service.IUserService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/login")
@CrossOrigin
@AllArgsConstructor
public class LoginController {
	
	private IUserService userService;

	@GetMapping("/validate/email/{email}")
	public ResponseEntity<?> validateEmail(@PathVariable("email") String email) {

		Boolean result = userService.checkValidUser(email);

		if (result) {

			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseDto(AppConstants.STATUS_200, AppConstants.MESSAGE_200));
		}

		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(new ResponseDto(AppConstants.STATUS_400, AppConstants.MESSAGE_400));

	}

	@PostMapping("/validate/user")
	public ResponseEntity<?> checkValidation(@RequestBody LoginDto loginDto) {
		 Map<String, String> userDetails = userService.checkValidPassword(loginDto);
		if (userDetails==null) {
			return ResponseEntity.status(HttpStatus.LOCKED).body("User Account locked");
			
		}
		return ResponseEntity.status(HttpStatus.OK)
				.body(userDetails);
	}	
	
	@PutMapping("/change-password")
	public ResponseEntity<?> updatePassword(@RequestBody LoginDto loginDto){
		
		Boolean status = userService.updatePassword(loginDto);
		
		if(status) {
			return ResponseEntity.status(HttpStatus.OK)
					.body("password is updated");
		}
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body("password updation is failed");
		
	}
	
	@PutMapping("/forgot-password")
	public ResponseEntity<?> forgotPassword(@RequestBody LoginDto loginDto){
		
		Boolean checkValidUser = userService.checkValidUser(loginDto.getEmail());
		if(checkValidUser) {
			userService.updatePassword(loginDto);
			return ResponseEntity.status(HttpStatus.OK).body("password is updated");
		}
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body("password updation is failed");
			
	}
	
	@GetMapping("/fetch-users")
	public ResponseEntity<?> fetchAllEmployees(){
		
		return ResponseEntity.status(HttpStatus.OK).body(userService.userDetails());
		
		
	}
	
	
	

}
