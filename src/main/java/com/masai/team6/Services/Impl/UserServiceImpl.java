package com.masai.team6.Services.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.masai.team6.Entities.Batch;
import com.masai.team6.Entities.Role;
import com.masai.team6.Entities.Section;
import com.masai.team6.Entities.User;
import com.masai.team6.Exception.ResourceNotFoundException;
import com.masai.team6.Payloads.UserDto;
import com.masai.team6.Repository.BatchRepo;
import com.masai.team6.Repository.RoleRepo;
import com.masai.team6.Repository.SectionRepo;
import com.masai.team6.Repository.UserRepo;
import com.masai.team6.Services.UserService;
import com.masai.team6.config.AppConstants;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private RoleRepo roleRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private SectionRepo sectionrepo;
	@Autowired
	private BatchRepo batchrepo;

	@Override
	public UserDto registerNewUser(UserDto userDto) {

		User user = this.modelMapper.map(userDto, User.class);
		user.setPassword(this.passwordEncoder.encode(user.getPassword()));
		Role role = this.roleRepo.findById(AppConstants.NORMAL_USER).get();
		user.getRoles().add(role);

		User newUser = this.userRepo.save(user);

		return this.modelMapper.map(newUser, UserDto.class);
	}

	@Override
	public UserDto createUser(UserDto userDto) {

		User user = this.modelMapper.map(userDto, User.class);
		User savedUser = this.userRepo.save(user);
		return this.modelMapper.map(savedUser, UserDto.class);
	}

	@Override
	public UserDto updateUser(UserDto user, Integer userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDto getUserById(Integer userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserDto> getAllUsers() {

		List<User> users = this.userRepo.findAll();

		List<UserDto> UserDtos = users.stream().map(user -> this.modelMapper.map(user, UserDto.class))
				.collect(Collectors.toList());
		return UserDtos;
	}

	@Override
	public void deleteUser(Integer userID) {
		// TODO Auto-generated method stub

	}

	@Override
	public UserDto registerAdmin(UserDto userDto) {
		User user = this.modelMapper.map(userDto, User.class);
		user.setPassword(this.passwordEncoder.encode(user.getPassword()));
		Role role = this.roleRepo.findById(AppConstants.ADMIN_USER).get();
		user.getRoles().add(role);
		User newUser = this.userRepo.save(user);

		return this.modelMapper.map(newUser, UserDto.class);
	}

	@Override
	public UserDto updatePassword(String email, String password) {
		Optional<User> user = this.userRepo.findByEmail(email);
		user.get().setPassword(this.passwordEncoder.encode(password));
		User newUser = this.userRepo.save(user.get());

		return this.modelMapper.map(newUser, UserDto.class);

	}

	public List<UserDto> getAdminUser() {
		List<User> users = this.userRepo.findAll();
		Role role = this.roleRepo.findById(AppConstants.ADMIN_USER).get();
		List<User> adminUsers = new ArrayList<User>();
		for (int i = 0; i < users.size(); i++) {
			if (users.get(i).getRoles().contains(role)) {
				adminUsers.add(users.get(i));
			}

		}

		List<UserDto> UserDtos = adminUsers.stream().map(user -> this.modelMapper.map(user, UserDto.class))
				.collect(Collectors.toList());
		return UserDtos;

	}

	public List<UserDto> getStudentUser() {
		List<User> users = this.userRepo.findAll();
		Role role = this.roleRepo.findById(AppConstants.NORMAL_USER).get();
		List<User> studentUsers = new ArrayList<User>();
		for (int i = 0; i < users.size(); i++) {
			if (users.get(i).getRoles().contains(role)) {
				studentUsers.add(users.get(i));
			}

		}

		List<UserDto> UserDtos = studentUsers.stream().map(user -> this.modelMapper.map(user, UserDto.class))
				.collect(Collectors.toList());
		return UserDtos;

	}
	
	public List<User> getUsersByBatchId(int batchId) {
        return userRepo.findByBatchId(batchId);
    }
	
}
