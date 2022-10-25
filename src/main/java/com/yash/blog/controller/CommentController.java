package com.yash.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.yash.blog.service.CommentService;
import com.yash.blog.utility.ApiResponse;
import com.yash.blog.utility.CommentDto;

@RestController
public class CommentController {
	
	@Autowired
	private CommentService commentservice;
	
	@PostMapping("/comment/post/{postid}")
	public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentdto, @PathVariable(name="postid") Integer postid)
	{
		CommentDto createdcomment= this.commentservice.createComment(commentdto, postid);
		return new ResponseEntity<CommentDto>(createdcomment, HttpStatus.OK);
	}
	
	@DeleteMapping("/comment/{commentid}")
	public ResponseEntity<ApiResponse> deleteComment(@PathVariable(name="commentid")Integer commentid)
	{
		this.commentservice.deleteComment(commentid);
		ApiResponse apiresponse= new ApiResponse("Comment deleted successfully!!",true);
		return new ResponseEntity<ApiResponse>(apiresponse, HttpStatus.OK);
	}

}
