package com.app.blog.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.blog.entity.PostEntity;



public interface PostRepository extends JpaRepository<PostEntity, Integer>{

	@Query("SELECT p FROM PostEntity p WHERE p.postTitle LIKE:title")
	List<PostEntity> searchByTitle(String title);
	
	
	
}
