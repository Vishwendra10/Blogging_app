package com.yash.blog.service;

import java.util.List;

import com.yash.blog.utility.CategoryDto;

public interface CategoryService {

	List<CategoryDto> getAllCategory();
	CategoryDto getCategoryById (Integer categoryid);
	CategoryDto createCategory (CategoryDto category);
	CategoryDto updateCategory (CategoryDto category, Integer categoryid);
	void deleteCategory(Integer categoryid);
}
