package com.masai.team6.Exception;


import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.masai.team6.Payloads.ApiResponse;
 

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> resourceNotFoundException(ResourceNotFoundException ex) {
		String message = ex.getMessage();
		ApiResponse apiResponce = new ApiResponse(message, false);

		return new ResponseEntity<ApiResponse>(apiResponce, HttpStatus.NOT_FOUND);
	}
	
	
	@ExceptionHandler(ApiException.class)
	public ResponseEntity<ApiResponse> HandleApiException(ApiException ex) {
		String message = ex.getMessage();
		ApiResponse apiResponce = new ApiResponse(message, true);

		return new ResponseEntity<ApiResponse>(apiResponce, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleMethodArgsNotValidException(MethodArgumentNotValidException ex){
		Map<String, String> response = new HashMap<>();
		
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError)error).getField();
			String message = error.getDefaultMessage();
			response.put(fieldName, message);
		});
		
		return new ResponseEntity<Map<String, String>> (response , HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(LectureException.class)
	public ResponseEntity<ErrorDetailsPage> handleLectureException(LectureException le, WebRequest req){
		
		ErrorDetailsPage erd = new ErrorDetailsPage();
		
		erd.setTimeStamp(LocalDateTime.now());
		erd.setMessage(le.getMessage());
		erd.setDetails(req.getDescription(false));
		
		return new ResponseEntity<ErrorDetailsPage>(erd,HttpStatus.BAD_REQUEST);
	}
}
	