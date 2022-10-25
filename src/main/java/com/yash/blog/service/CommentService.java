package com.yash.blog.service;

import com.yash.blog.utility.CommentDto;

public interface CommentService {
	
	CommentDto createComment(CommentDto commentdto, Integer postid);
	void deleteComment(Integer commentid);

}
