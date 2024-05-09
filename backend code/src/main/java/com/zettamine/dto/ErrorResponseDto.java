package com.zettamine.dto;

import java.time.LocalDateTime;
import java.time.LocalTime;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponseDto {
	private String apipath;
	private HttpStatus errorCode;
	private String errorMessage;
	private LocalTime errorTime;

}
