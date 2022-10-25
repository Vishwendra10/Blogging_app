package com.yash.blog.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.yash.blog.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
