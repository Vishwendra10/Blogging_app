package com.yash.blog.controller;

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
import org.springframework.web.bind.annotation.RestController;

import com.yash.blog.exceptions.ApiException;
import com.yash.blog.security.JwtTokenHelper;
import com.yash.blog.service.UserService;
import com.yash.blog.utility.JwtAuthRequest;
import com.yash.blog.utility.JwtAuthResponse;
import com.yash.blog.utility.UserDto;

@RestController
@RequestMapping("/v1/auth/")
public class AuthController {
	
	@Autowired
	private JwtTokenHelper jwttokenhelper;
	
	@Autowired
	private UserDetailsService userdetailsservice;
	
	@Autowired
	private AuthenticationManager authenticationmanager;
	
	@Autowired
	private UserService userservice;
	
	@PostMapping("/login")
	public ResponseEntity<JwtAuthResponse> createToken(@RequestBody JwtAuthRequest request)
	{
		this.authenticate(request.getUsername(), request.getPassword());
		
		UserDetails userdetails= this.userdetailsservice.loadUserByUsername(request.getUsername());
		
		String token= this.jwttokenhelper.generateToken(userdetails);
		
		JwtAuthResponse response= new JwtAuthResponse();
		response.setToken(token);
		
		return new ResponseEntity<JwtAuthResponse>(response,HttpStatus.OK);
	}
	
	
	private void authenticate (String username, String password){
		 UsernamePasswordAuthenticationToken authenticationtoken= new UsernamePasswordAuthenticationToken(username,password);
		 
		 try {
			 this.authenticationmanager.authenticate(authenticationtoken);
		 }
		 catch(BadCredentialsException e)
		 {
			 throw new ApiException("Invalid password");
		 }
	}
	
	//Register new user
	@PostMapping("/register")
	public ResponseEntity<UserDto> registerUser(@RequestBody UserDto userdto)
	{
		UserDto registeredUser= this.userservice.registerUser(userdto);
		return new ResponseEntity<UserDto>(registeredUser,HttpStatus.CREATED);
	}

}
