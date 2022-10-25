package com.yash.blog.controller;
/**
 * @author vishwendra.singh
 */
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.yash.blog.serviceimpl.UserServiceImpl;
import com.yash.blog.utility.UserDto;

@RestController
public class UserController {
	
	@Autowired
	private UserServiceImpl userserviceimpl;
	
	@GetMapping("/users")
	public ResponseEntity<List<UserDto>> getAllUsers()
	{
		List<UserDto> list= this.userserviceimpl.getAllUser();
		return new ResponseEntity<List<UserDto>>(list,HttpStatus.OK);
	}
	
	@GetMapping("/users/{id}")
	public ResponseEntity<UserDto> getUserById(@PathVariable(value = "id") int id)
	{
		UserDto userdto=this.userserviceimpl.getUserById(id);
		return new ResponseEntity<UserDto>(userdto, HttpStatus.OK);
	}
	
	@PostMapping("/users")
	public ResponseEntity<UserDto> createUser(@RequestBody UserDto user)
	{
		UserDto userdto= this.userserviceimpl.createUser(user);
		return new ResponseEntity<UserDto>(userdto, HttpStatus.OK);
	}
	
	@PutMapping("/users/{id}")
	public ResponseEntity<UserDto> updateUser(@PathVariable(value="id") int id, @RequestBody UserDto user)
	{
		UserDto userdto=this.userserviceimpl.updateUser(user, id);
		return new ResponseEntity<UserDto>(userdto,HttpStatus.OK);
	}
	
	//Or we can use .antMatchers("URL").hasRole("ADMIN") in SecurityConfig class
	//Or we can add another .antMatchers(HttpMethod.GET).permitAll()
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/users/{id}")
	public ResponseEntity<String> deleteUserById(@PathVariable(value= "id") int id)
	{
		this.userserviceimpl.deleteUser(id);
		return new ResponseEntity<String>("Deleted", HttpStatus.OK);
	}

}
