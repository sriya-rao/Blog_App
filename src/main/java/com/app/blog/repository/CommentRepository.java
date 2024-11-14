package com.app.blog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.blog.entity.CommentEntity;
import com.app.blog.entity.PostEntity;

public interface CommentRepository extends JpaRepository<CommentEntity, Integer>{

	
	List<CommentEntity> findByPost(PostEntity post);
}
