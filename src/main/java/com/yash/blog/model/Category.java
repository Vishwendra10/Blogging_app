package com.yash.blog.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="Categories")
@Getter
@Setter
@NoArgsConstructor
public class Category {
	
	@Id
	@GeneratedValue
	private Integer categoryid;
	
	private String categoryTitle;
	
	@Column(name="Description")
	private String categoryDescription;
	
	//Cascade is used to inherit parents operations. If parent gets update, child automaticlly gets updated
	@OneToMany(mappedBy= "category", cascade= CascadeType.ALL, fetch=FetchType.LAZY)
	private List<Post> posts= new ArrayList<>();
	


}
