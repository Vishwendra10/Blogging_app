package com.yash.blog.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yash.blog.model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer>{

}
