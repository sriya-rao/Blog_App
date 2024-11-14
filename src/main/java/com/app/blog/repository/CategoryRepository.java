package com.app.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.blog.entity.CategoryEntity;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Integer>{

}
