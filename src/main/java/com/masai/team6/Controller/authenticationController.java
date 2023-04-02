package com.masai.team6.Controller;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.Random;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.team6.Entities.User;
import com.masai.team6.Entities.otpsaver;
import com.masai.team6.Exception.ApiException;
import com.masai.team6.Payloads.JwtAuthRequest;
import com.masai.team6.Payloads.UserDto;
import com.masai.team6.Repository.UserRepo;
import com.masai.team6.Services.EmailService;
import com.masai.team6.Services.UserService;
import com.masai.team6.Services.Impl.otpServiceImpl;
import com.masai.team6.security.JwtAuthResponse;
import com.masai.team6.security.JwtTokenHelper;

@RestController
@RequestMapping("/api/v1/auth/")
public class authenticationController {

	@Autowired
	private JwtTokenHelper jwtTokenHelper;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private UserService userService;

	@Autowired
	private UserRepo userrepo;

	@Autowired
	private ModelMapper mapper;
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private EmailService emailservice;

	@Autowired
	private otpServiceImpl otpservice;

	@PostMapping("/login")
	public ResponseEntity<JwtAuthResponse> createToken(@RequestBody JwtAuthRequest request

	) throws Exception {

		this.authenticate(request.getUsername(), request.getPassword());
		UserDetails userDetails = this.userDetailsService.loadUserByUsername(request.getUsername());
		String generateToken = this.jwtTokenHelper.generateToken(userDetails);

		JwtAuthResponse response = new JwtAuthResponse();
		response.setToken(generateToken);
		response.setUser(this.mapper.map((User) userDetails, UserDto.class));
		return new ResponseEntity<JwtAuthResponse>(response, HttpStatus.OK);

	}

	private void authenticate(String username, String password) throws Exception {

		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,
				password);
		try {
			this.authenticationManager.authenticate(authenticationToken);
		} catch (BadCredentialsException e) {
			System.out.println("invalid details !! ");
			throw new ApiException("invalid username password");
		}

	}

	@PostMapping("/register/user")
	public ResponseEntity<String> registerUser(@Valid @RequestBody UserDto userDto) {
		if (userrepo.findByEmail(userDto.getEmail()).isEmpty()) {
			UserDto registerNewUser = this.userService.registerNewUser(userDto);
			if (registerNewUser != null)
				return new ResponseEntity<String>("Success", HttpStatus.OK);
			else {
				return new ResponseEntity<String>("User not Registered", HttpStatus.FORBIDDEN);
			}
		} else {
			return new ResponseEntity<String>("User already registered with this Email", HttpStatus.FORBIDDEN);

		}

	}

	@PostMapping("/register/admin")
	public ResponseEntity<String> registerAdmin(@Valid @RequestBody UserDto userDto) {
		if (userrepo.findByEmail(userDto.getEmail()).isEmpty()) {
			UserDto registerAdmin = this.userService.registerAdmin(userDto);

			if (registerAdmin != null)
				return new ResponseEntity<String>("Success", HttpStatus.OK);
			else {
				return new ResponseEntity<String>("Admin not Registered", HttpStatus.FORBIDDEN);
			}
		} else {
			return new ResponseEntity<String>("Admin already registered with this Email", HttpStatus.FORBIDDEN);
		}

	}

	@PostMapping("/send-otp")
	public ResponseEntity<String> sendOTP(@Valid @RequestBody otpsaver otpSaver0) {
		String email = otpSaver0.getEmail();

		Optional<User> user = this.userrepo.findByEmail(email);

		if (user.isEmpty()) {
			return new ResponseEntity<String>("User don't exist with this email :" + email, HttpStatus.NOT_FOUND);
		} else {

			System.out.println("Email " + email);
			int minimum = 100000;
			int maximum = 999999;
			Random random = new Random(); // todo ;- try to use secure random

			int otp = minimum + random.nextInt((maximum - minimum) + 1);

			System.out.println("OTP : " + otp);
			String subject = "OTP from Masai ";
			String message = "your OTP :" + otp + " please don't share with anyone";
			String to = email;

			boolean flag = this.emailservice.sendEmail(to, subject, message);

			if (flag) {

				otpsaver otpSaver = new otpsaver(email, otp, LocalDateTime.now());
				otpsaver saveotp = otpservice.Saveotp(otpSaver);

				saveotp.toString();
				return new ResponseEntity<String>("Otp send Successfully", HttpStatus.OK);
			} else {

				return new ResponseEntity<String>("please check your email : " + email, HttpStatus.BAD_GATEWAY);
			}
		}
	}

	@PostMapping("/verify-otp")
	public ResponseEntity<String> verifyOtp(@RequestBody otpsaver otpSaver0) {
		String email = otpSaver0.getEmail();
		int otp = otpSaver0.getOtp();
		otpsaver otpobject = this.otpservice.FindByEmail(email);
		int myOtp = otpobject.getOtp();
		LocalDateTime oldDate = otpobject.getOtpRequestedTime();
		LocalDateTime newDate = LocalDateTime.now();
		long minutes = ChronoUnit.MINUTES.between(oldDate, newDate);
		if (minutes > 5) {

			this.otpservice.clearOTP(otpobject);
			return new ResponseEntity<String>("Otp Expired , Please regenerate OTP", HttpStatus.NOT_ACCEPTABLE);

		}

		if (myOtp == otp) {

			Optional<User> user = userrepo.findByEmail(email);

			if (user.get() == null) {
				return new ResponseEntity<String>("user not found with this email :" + email, HttpStatus.NOT_FOUND);
			}

			this.otpservice.clearOTP(otpobject);
			return new ResponseEntity<String>("otp verified", HttpStatus.ACCEPTED);
		} else
			return new ResponseEntity<String>("otp wrong", HttpStatus.UNAUTHORIZED);

	}

	@PostMapping("/updatepassword")
	public ResponseEntity<String> updatepassword(@Valid @RequestBody UserDto userDto0) {
		String email = userDto0.getEmail();
		String password = userDto0.getPassword();
		UserDto userDto = this.userService.updatePassword(email, password);
		if (userDto != null)
			return new ResponseEntity<String>("Password update successfully", HttpStatus.OK);
		else {
			return new ResponseEntity<String>("Password not updated ", HttpStatus.BAD_GATEWAY);

		}
	}

}
