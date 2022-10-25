package com.yash.blog.utility;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.yash.blog.model.Comment;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostDto {
	
	private Integer postid;
	private String title;
	private String content;
	private String imagename;
	private Date adddate;
	private CategoryDto category;
	private UserDto user;
	private Set<CommentDto> comments= new HashSet<>();

}
