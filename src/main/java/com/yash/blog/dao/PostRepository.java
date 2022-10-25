package com.yash.blog.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yash.blog.model.Category;
import com.yash.blog.model.Post;
import com.yash.blog.model.User;

public interface PostRepository extends JpaRepository<Post, Integer> {

	List<Post> findByUser(User user);
	List<Post> findByCategory(Category category);
}
