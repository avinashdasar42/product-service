package com.api.thirdparty.product_service.dtos;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiErrorDto {

	private String message;
	private HttpStatus status;
	private LocalDateTime ldt;
	
}
