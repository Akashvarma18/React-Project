package com.zettamine.exception;


import java.time.LocalTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.zettamine.dto.ErrorResponseDto;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(ResourceAlreadyExistsException.class)
	public ResponseEntity<ErrorResponseDto> resourceAlreadyExists(WebRequest request, ResourceAlreadyExistsException ex){
		ErrorResponseDto errorDto = new ErrorResponseDto();
		errorDto.setApipath(request.getDescription(false));
		errorDto.setErrorCode(HttpStatus.BAD_REQUEST);
		errorDto.setErrorMessage(ex.getMessage());
		errorDto.setErrorTime(LocalTime.now());
		return new ResponseEntity<ErrorResponseDto>(errorDto, HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorResponseDto> resourceNotFound(WebRequest request, ResourceNotFoundException ex){
		ErrorResponseDto errResponseDto = new ErrorResponseDto();
		errResponseDto.setApipath(request.getDescription(false));
		errResponseDto.setErrorCode(HttpStatus.BAD_REQUEST);
		errResponseDto.setErrorMessage(ex.getMessage());
		errResponseDto.setErrorTime(LocalTime.now());
		return new ResponseEntity<ErrorResponseDto>(errResponseDto, HttpStatus.BAD_REQUEST);
	}
	
	
	
	
	
	
	
	

}
