package com.yash.blog.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.yash.blog.utility.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler (ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> ResourceNotFoundExceptionHandler(ResourceNotFoundException re){
		
		String message=re.getMessage();
		ApiResponse apiresponse= new ApiResponse(message, false);
		return new ResponseEntity<ApiResponse>(apiresponse, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handlingMethodArgumentNotValidException(MethodArgumentNotValidException ex)
	{
		Map<String,String> response= new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error)->{
			String fieldName=((FieldError) error).getField();
			String message= error.getDefaultMessage();
			response.put(fieldName, message);
		});
		return new ResponseEntity<Map<String, String>>(response,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler (ApiException.class)
	public ResponseEntity<ApiResponse> ApiExceptionHandler(ApiException ae){
		
		String message=ae.getMessage();
		ApiResponse apiresponse= new ApiResponse(message, true);
		return new ResponseEntity<ApiResponse>(apiresponse, HttpStatus.BAD_REQUEST);
	}

}
