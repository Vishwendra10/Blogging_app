package com.yash.blog.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.yash.blog.service.CategoryService;
import com.yash.blog.utility.ApiResponse;
import com.yash.blog.utility.CategoryDto;

@RestController
public class CategoryController {
	
	@Autowired
	private CategoryService categoryservice;
	
	@GetMapping("/category")
	public ResponseEntity<List<CategoryDto>> getAllCategory()
	{
		List<CategoryDto> list= this.categoryservice.getAllCategory();
		return new ResponseEntity<List<CategoryDto>>(list, HttpStatus.OK);
	}
	
	@GetMapping("/category/{id}")
	public ResponseEntity<CategoryDto> getById(@PathVariable(value="id") Integer id)
	{
		CategoryDto categorydto= this.categoryservice.getCategoryById(id);
		return new ResponseEntity<CategoryDto>(categorydto,HttpStatus.OK);
	}
	
	@PostMapping("/category")
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categorydto)
	{
		CategoryDto createdcategorydto=this.categoryservice.createCategory(categorydto);
		return new ResponseEntity<CategoryDto>(createdcategorydto, HttpStatus.CREATED);
	}
	
	@PutMapping("/category/{id}")
	public ResponseEntity<CategoryDto> updateCategory(@Valid @PathVariable(value="id") Integer categoryid,@RequestBody CategoryDto categorydto)
	{
		CategoryDto category=this.categoryservice.updateCategory(categorydto, categoryid);
		return new ResponseEntity<CategoryDto>(category, HttpStatus.OK);
	}
	
	@DeleteMapping("/category/{id}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable(value="id") Integer categoryid)
	{
		this.categoryservice.deleteCategory(categoryid);
		ApiResponse apiresponse= new ApiResponse("Category is deleted !!",true);
		return new ResponseEntity<ApiResponse>(apiresponse, HttpStatus.OK);
	}
	

}
