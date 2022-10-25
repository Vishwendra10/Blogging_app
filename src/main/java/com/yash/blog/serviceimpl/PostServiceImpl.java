package com.yash.blog.serviceimpl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yash.blog.dao.CategoryRepository;
import com.yash.blog.dao.PostRepository;
import com.yash.blog.dao.UserReopsitory;
import com.yash.blog.exceptions.ResourceNotFoundException;
import com.yash.blog.model.Category;
import com.yash.blog.model.Post;
import com.yash.blog.model.User;
import com.yash.blog.service.PostService;
import com.yash.blog.utility.PostDto;

@Service
public class PostServiceImpl implements PostService {
	
	@Autowired
	private PostRepository postrepo;
	@Autowired
	private UserReopsitory userrepo;
	@Autowired
	private CategoryRepository catrepo;
	@Autowired
	private ModelMapper modelmapper;

	@Override
	public List<PostDto> getAllpost() {
		// TODO Auto-generated method stub
		List<Post> list=postrepo.findAll();
		List<PostDto> listdto= list.stream().map(e-> this.postToDto(e)).collect(Collectors.toList());
		return listdto;
	}

	@Override
	public PostDto getPostById(Integer postid) {
		// TODO Auto-generated method stub
		Post post=this.postrepo.findById(postid).orElseThrow(()-> new ResourceNotFoundException("Post","id",postid));
		return this.postToDto(post);
	}

	@Override
	public PostDto createPost(PostDto postdto, Integer userid, Integer categoryid) {
		// TODO Auto-generated method stub
		
		User user= this.userrepo.findById(userid).orElseThrow(()->new ResourceNotFoundException("User","id", userid));
		
		Category category= this.catrepo.findById(categoryid).orElseThrow(()-> new ResourceNotFoundException("Category","id", categoryid));
		
		Post post=this.modelmapper.map(postdto, Post.class);
		if(postdto.getImagename()!=null)
		{
			post.setImagename(postdto.getImagename());
		}
		else
		{
			post.setImagename("default.png");
		}
		
		post.setAdddate(new Date());
		post.setUser(user);
		post.setCategory(category);
		
		Post newpost=this.postrepo.save(post);
		
		return this.modelmapper.map(newpost, PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postdto, Integer postid) {
		// TODO Auto-generated method stub
		Post post=this.postrepo.findById(postid).orElseThrow(()-> new ResourceNotFoundException("Post","id",postid));
		
		post.setTitle(postdto.getTitle());
		post.setContent(postdto.getContent());
		post.setImagename(postdto.getImagename());

		Post updatedpost=this.postrepo.save(post);
		return this.postToDto(updatedpost);
	}

	@Override
	public void deletePost(Integer postid) {
		// TODO Auto-generated method stub
		this.postrepo.findById(postid).orElseThrow(()->new ResourceNotFoundException("Post","id",postid));
		this.postrepo.deleteById(postid);
		
	}

	@Override
	public List<PostDto> getPostByCategory(Integer categoryid) {
		// TODO Auto-generated method stub
		
		Category category=this.catrepo.findById(categoryid).orElseThrow(()->new ResourceNotFoundException("Category","id",categoryid));
		List<Post> list=this.postrepo.findByCategory(category);
		List<PostDto> listdto=list.stream().map(e-> this.postToDto(e)).collect(Collectors.toList());
		return listdto;
	}

	@Override
	public List<PostDto> getPostByUser(Integer userid) {
		// TODO Auto-generated method stub
		User user= this.userrepo.findById(userid).orElseThrow(()-> new ResourceNotFoundException("User","id", userid));
		List<Post> list=this.postrepo.findByUser(user);
		List<PostDto> listdto=list.stream().map(e-> this.postToDto(e)).collect(Collectors.toList());
		return listdto;
	}
	
	public PostDto postToDto(Post post)
	{
		PostDto postdto=this.modelmapper.map(post, PostDto.class);
		return postdto;
	}
	
	public Post dtoToPost(PostDto postdto)
	{
		Post post= this.modelmapper.map(postdto, Post.class);
		return post;
	}

}
