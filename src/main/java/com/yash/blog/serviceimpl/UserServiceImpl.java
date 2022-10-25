package com.yash.blog.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.yash.blog.config.AppConstants;
import com.yash.blog.dao.RoleRepository;
import com.yash.blog.dao.UserReopsitory;
import com.yash.blog.exceptions.ResourceNotFoundException;
import com.yash.blog.model.Role;
import com.yash.blog.model.User;
import com.yash.blog.service.UserService;
import com.yash.blog.utility.UserDto;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserReopsitory userrepository;
	
	@Autowired
	private ModelMapper modelmapper;
	
	@Autowired
	private PasswordEncoder passwordencoder;
	
	@Autowired
	private RoleRepository rolerepository;
	
	@Override
	public List<UserDto> getAllUser() {
		// TODO Auto-generated method stub
		List<User> listinuser= this.userrepository.findAll();
		List<UserDto> listindto=listinuser.stream().map(e-> this.userToDto(e)).collect(Collectors.toList());
		return listindto;
	}
	
	@Override
	public UserDto getUserById(Integer userId) {
		// TODO Auto-generated method stub
		User user=this.userrepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","id",userId));
		return this.userToDto(user);
	}

	@Override
	public UserDto createUser(UserDto userdto) {
		// TODO Auto-generated method stub
		User user=dtoToUser(userdto);
		user.setPassword(this.passwordencoder.encode(user.getPassword()));
		User saveduser=this.userrepository.save(user);
		return this.userToDto(saveduser);
	}

	@Override
	public UserDto updateUser(UserDto userdto, Integer userId) {
		// TODO Auto-generated method stub
		
		this.userrepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "id", userId));
		User user1=this.dtoToUser(userdto);
		if(user1.getPassword()!=null)
		{
			user1.setPassword(this.passwordencoder.encode(user1.getPassword()));
		}
		User updateduser=this.userrepository.save(user1);
		return this.userToDto(updateduser);
	}

	@Override
	public void deleteUser(Integer userId) {
		// TODO Auto-generated method stub
		User user= this.userrepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","id", userId));
		this.userrepository.delete(user);
	}
	
	@Override
	public UserDto registerUser(UserDto userdto) {
		User user= this.dtoToUser(userdto);
		Role role=this.rolerepository.findById(AppConstants.ROLE_NORMAL).get();
		user.getRoles().add(role);
		user.setPassword(this.passwordencoder.encode(user.getPassword()));
		
		User registeredUser = this.userrepository.save(user);
		return this.userToDto(registeredUser);
	}

	//ModelMapping
	public UserDto userToDto(User user)
	{
		UserDto userdto= this.modelmapper.map(user, UserDto.class);
		return userdto;
	}
	public User dtoToUser(UserDto userdto)
	{
		User user=this.modelmapper.map(userdto, User.class);
		return user;
	}

	

}
