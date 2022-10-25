package com.yash.blog.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="post")
@Setter
@Getter
@NoArgsConstructor
public class Post {
	
	@Id
	@GeneratedValue
	private Integer postid;
	
	private String title;
	
	private String content;
	
	private String imagename;
	
	private Date adddate;
	
	@ManyToOne
	@JoinColumn(name= "category_id")
	private Category category;
	
	@ManyToOne
	private User user;
	
	@OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
	private Set<Comment> comments= new HashSet<>();
}
