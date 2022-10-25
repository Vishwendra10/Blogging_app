package com.yash.blog.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yash.blog.model.User;

public interface UserReopsitory extends JpaRepository<User, Integer>{
	
	Optional<User> findByEmail(String email);
}
