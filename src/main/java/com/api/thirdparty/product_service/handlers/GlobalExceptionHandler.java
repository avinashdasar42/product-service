package com.api.thirdparty.product_service.handlers;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.api.thirdparty.product_service.dtos.ApiErrorDto;
import com.api.thirdparty.product_service.exceptions.ProductNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ProductNotFoundException.class)
	public ResponseEntity<Object> handleProductNotFoundException(ProductNotFoundException ex){
		Map<String, Object> map = new HashMap<>();
		ApiErrorDto dto = new ApiErrorDto();
		dto.setLdt(LocalDateTime.now());
		dto.setMessage(ex.getMessage());
		dto.setStatus(HttpStatus.BAD_REQUEST);
		map.put("apiErrors", dto);
		return new ResponseEntity<>(map, dto.getStatus());		
	}

}
