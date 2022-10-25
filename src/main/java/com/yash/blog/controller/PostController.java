package com.yash.blog.controller;

import java.io.IOException;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.yash.blog.service.FileService;
import com.yash.blog.service.PostService;
import com.yash.blog.utility.ApiResponse;
import com.yash.blog.utility.PostDto;

@RestController
public class PostController {
	
	@Autowired
	private PostService postservice;
	
	@Autowired
	private FileService fileserivce;
	
	@Value("${project.image}")
	private String path;
	
	@PostMapping("/user/{userid}/category/{categoryid}/post")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postdto, @PathVariable(name="userid") int userid, @PathVariable(name="categoryid")int catid)
	{
		PostDto post1= this.postservice.createPost(postdto, userid, catid);
		return new ResponseEntity<PostDto>(post1, HttpStatus.CREATED);
	}
	
	@PutMapping("post/{id}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto post, @PathVariable(name="id") Integer postid)
	{
		PostDto postdto= this.postservice.updatePost(post, postid);
		return new ResponseEntity<PostDto>(postdto, HttpStatus.CREATED);
	}
	
	@GetMapping("/post")
	public ResponseEntity<List<PostDto>> getAllPost()
	{
		List<PostDto>list=this.postservice.getAllpost();
		return new ResponseEntity<List<PostDto>>(list, HttpStatus.OK);
	}
	
	@GetMapping("/post/{id}")
	public ResponseEntity<PostDto> getPostById(@PathVariable(name="id") Integer postid)
	{
		PostDto postdto= this.postservice.getPostById(postid);
		return new ResponseEntity<PostDto>(postdto, HttpStatus.OK);
	}
	
	@GetMapping("/user/{id}/post")
	public ResponseEntity<List<PostDto>> getPostBtUser(@PathVariable(name="id") Integer userid)
	{
		List<PostDto> list= this.postservice.getPostByUser(userid);
		return new ResponseEntity<List<PostDto>>(list, HttpStatus.OK);
	}
	
	@GetMapping("/category/{id}/post")
	public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable(name="id") Integer catid)
	{
		List<PostDto> list=this.postservice.getPostByCategory(catid);
		return new ResponseEntity<List<PostDto>>(list, HttpStatus.OK);
	}
	
	@DeleteMapping("/post/{id}")
	public ResponseEntity<ApiResponse> deletePost(@PathVariable(name= "id") Integer postid)
	{
		this.postservice.deletePost(postid);
		ApiResponse apiresponse= new ApiResponse("Post deleted Successfully!!",true);
		return new ResponseEntity<ApiResponse>(apiresponse, HttpStatus.OK);
	}
	
	//post image upload
	@PostMapping("/post/image/upload/{postid}")
	public ResponseEntity<PostDto> uploadImage(@RequestParam("image") MultipartFile image,@PathVariable(name= "postid" ) Integer postid) throws IOException
	{
		PostDto postdto= this.postservice.getPostById(postid);
		
		String fileName= this.fileserivce.uploadImage(path, image);
		
		postdto.setImagename(fileName);
		PostDto updatedpost= this.postservice.updatePost(postdto, postid);
		
		return new ResponseEntity<PostDto>(updatedpost,HttpStatus.OK);
	}

}
