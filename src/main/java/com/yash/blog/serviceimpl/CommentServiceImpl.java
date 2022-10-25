package com.yash.blog.serviceimpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yash.blog.dao.CommentRepository;
import com.yash.blog.dao.PostRepository;
import com.yash.blog.exceptions.ResourceNotFoundException;
import com.yash.blog.model.Comment;
import com.yash.blog.model.Post;
import com.yash.blog.service.CommentService;
import com.yash.blog.utility.CommentDto;

@Service
public class CommentServiceImpl implements CommentService{

	@Autowired
	private PostRepository postrepo;
	
	@Autowired
	private CommentRepository commentrepo;
	
	@Autowired
	private ModelMapper modelmapper;
	
	@Override
	public CommentDto createComment(CommentDto commentdto, Integer postid) {
		
		Post post= this.postrepo.findById(postid).orElseThrow(()-> new ResourceNotFoundException("Post","id",postid));
		Comment comment = this.DtoToComment(commentdto);
		comment.setPost(post);
		Comment savedcomment= this.commentrepo.save(comment);
		return this.commentToDto(savedcomment);
	}

	@Override
	public void deleteComment(Integer commentid) {
		
		Comment comment= this.commentrepo.findById(commentid).orElseThrow(()-> new ResourceNotFoundException("Comment","id",commentid));
		this.commentrepo.deleteById(commentid);
	}
	
	public CommentDto commentToDto(Comment comment) {
		
		CommentDto commentdto= this.modelmapper.map(comment, CommentDto.class);
		return commentdto;
	}
	
	public Comment DtoToComment(CommentDto commentdto) {
		
		Comment comment= this.modelmapper.map(commentdto, Comment.class);
		return comment;
	}

}
