package com.yash.blog.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.yash.blog.dao.UserReopsitory;
import com.yash.blog.exceptions.ResourceNotFoundException;
import com.yash.blog.model.User;

@Service
public class CustomUserDetailsService implements UserDetailsService{
	
	@Autowired
	private UserReopsitory userrepository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// loading user from database by username
		User user= this.userrepository.findByEmail(username).orElseThrow(()-> new ResourceNotFoundException("User","email: "+username,0));
		
		return user;
	}

}