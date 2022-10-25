package com.yash.blog.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yash.blog.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer>{

}
