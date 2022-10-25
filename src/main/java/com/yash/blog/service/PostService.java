package com.yash.blog.service;

import java.util.List;

import com.yash.blog.model.Post;
import com.yash.blog.utility.PostDto;

public interface PostService {
	
	//get
	List<PostDto> getAllpost();
	
	//getByid
	PostDto getPostById(Integer postid);
	
	//create
	PostDto createPost(PostDto postdto, Integer userid, Integer categoryid);
	
	//update
	PostDto updatePost(PostDto postdto, Integer postid);
	
	//delete
	void deletePost(Integer postid);
	
	//all post by category
	List<PostDto> getPostByCategory(Integer categoryid);
	
	//all post by user
	List<PostDto> getPostByUser(Integer userid);
	
	

}
