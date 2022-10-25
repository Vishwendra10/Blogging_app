package com.yash.blog.utility;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDto {
	
	private Integer categoryid;
	
	@NotBlank
	@Size(min=3, message ="Min size of Title is 3")
	private String categoryTitle;
	
	@NotBlank
	@Size(min=5)
	private String categoryDescription;

}
