package com.masai.team6.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masai.team6.Entities.User;
import com.masai.team6.Payloads.UserDto;
import com.masai.team6.Services.UserService;

@RestController
@RequestMapping("/api/users")

public class UserController {
	
	@Autowired
	private UserService userService;
	
	// create user ------->
	
	@PostMapping("/")
	public ResponseEntity<UserDto> CreateUser(@RequestBody UserDto userDto ){
		UserDto createUserDto= this.userService.createUser(userDto);
		return new ResponseEntity<UserDto>(createUserDto, HttpStatus.CREATED);
	}
	
	
	@GetMapping("/adminusers")
	public ResponseEntity<List<UserDto>> getAdmins(){
		List<UserDto> ans = this.userService.getAdminUser();
		return new ResponseEntity<List<UserDto>>(ans, HttpStatus.OK);
	}
	
	@GetMapping("/studentUsers")
	public ResponseEntity<List<UserDto>> getAllStudents(){
		List<UserDto> ans = this.userService.getStudentUser();
		return new ResponseEntity<List<UserDto>>(ans, HttpStatus.OK);
	}

	
	@GetMapping("/{batchId}")
	public ResponseEntity<List<User>> getUserByBatch(@PathVariable Integer batchId){
		List<User> users = userService.getUsersByBatchId(batchId);
		
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
		
	}
}
