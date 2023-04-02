package com.masai.team6.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.masai.team6.Entities.User;
import com.masai.team6.Exception.ResourceNotFoundException;
import com.masai.team6.Repository.UserRepo;

 

@Service
public class CustomUserDetailService implements UserDetailsService {

	@Autowired
	private UserRepo userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = this.userRepo.findByEmail(username).orElseThrow(()-> new ResourceNotFoundException("User","email : " + username,0));

		return user;
	}

}
