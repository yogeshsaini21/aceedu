package com.masai.team6.security;

import com.masai.team6.Payloads.UserDto;

import lombok.Data;

@Data
public class JwtAuthResponse {

	private String token;
	private UserDto user;
}
