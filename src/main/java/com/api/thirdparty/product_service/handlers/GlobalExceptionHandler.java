package com.api.thirdparty.product_service.handlers;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.api.thirdparty.product_service.dtos.ApiErrorDto;
import com.api.thirdparty.product_service.exceptions.ProductNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ProductNotFoundException.class)
	public ResponseEntity<Object> handleProductNotFoundException(ProductNotFoundException ex){
		Map<String, Object> map = new HashMap<>();
		ApiErrorDto dto = new ApiErrorDto();
		dto.setLdt(LocalDateTime.now());
		dto.setMessage(ex.getMessage());
		dto.setStatus(HttpStatus.NOT_FOUND);
		map.put("apiErrors", dto);
		return new ResponseEntity<>(map, dto.getStatus());		
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Object> handleGenericException(MethodArgumentNotValidException ex){
		List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.toList());

		Map<String, List<String>> body = new HashMap<>();
		body.put("errors", errors);

		return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);		
	}

}
