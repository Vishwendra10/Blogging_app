package com.yash.blog.utility;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.yash.blog.model.Role;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {
	
	private int id;
	private String name;
	private String email;
	@JsonIgnore
	private String password;
	private String about;
	
	@JsonProperty
    public void setPassword(final String password)
	{
		this.password = password;
	}
	
	private Set<RoleDto> roles= new HashSet<>();
}
