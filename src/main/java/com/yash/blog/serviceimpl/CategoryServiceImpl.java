package com.yash.blog.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yash.blog.dao.CategoryRepository;
import com.yash.blog.exceptions.ResourceNotFoundException;
import com.yash.blog.model.Category;
import com.yash.blog.service.CategoryService;
import com.yash.blog.utility.CategoryDto;

@Service
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	private CategoryRepository catrepo;
	
	@Autowired
	private ModelMapper modelmapper;
	
	@Override
	public List<CategoryDto> getAllCategory() {
		// TODO Auto-generated method stub
		List<Category> list=this.catrepo.findAll();
		List<CategoryDto> listdto= list.stream().map(e-> this.categoryToDto(e)).collect(Collectors.toList());
		return listdto;
	}

	@Override
	public CategoryDto getCategoryById(Integer categoryid) {
		// TODO Auto-generated method stub
		Category category= this.catrepo.findById(categoryid).orElseThrow(()-> new ResourceNotFoundException("Category","id",categoryid));
		return this.categoryToDto(category);
	}

	@Override
	public CategoryDto createCategory(CategoryDto categorydto) {
		// TODO Auto-generated method stub
		
		Category category= this.catrepo.save(this.dtoToCategory(categorydto));
		return this.categoryToDto(category);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categorydto, Integer categoryid) {
		// TODO Auto-generated method stub
		this.catrepo.findById(categoryid).orElseThrow(()-> new ResourceNotFoundException("Category","id", categoryid));
		Category category= this.catrepo.save(this.dtoToCategory(categorydto));
		return this.categoryToDto(category);
	}

	@Override
	public void deleteCategory(Integer categoryid) {
		// TODO Auto-generated method stub
		this.catrepo.findById(categoryid).orElseThrow(()-> new ResourceNotFoundException("Category","id", categoryid));
		this.catrepo.deleteById(categoryid);;
		
	}
	
	public CategoryDto categoryToDto(Category category)
	{
		CategoryDto categorydto=this.modelmapper.map(category, CategoryDto.class);
		return categorydto;
	}
	
	public Category dtoToCategory(CategoryDto categorydto)
	{
		Category category= this.modelmapper.map(categorydto, Category.class);
		return category;
	}

}
