package com.app.blog.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.blog.entity.CommentEntity;
import com.app.blog.entity.PostEntity;
import com.app.blog.exceptions.ResourceNotFoundException;
import com.app.blog.repository.CommentRepository;
import com.app.blog.repository.PostRepository;
import com.app.blog.request.Comment;
import com.app.blog.request.Post;
import com.app.blog.service.CommentService;

@Service
public class CommentImplClass implements CommentService{
	
	@Autowired
	PostRepository postRepository;
	
	@Autowired
	CommentRepository repository;

	@Override
	public Integer createComment(Comment comment, Integer postId) {
		PostEntity postEntity=postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post Not Found with id:"+postId));		
		CommentEntity commentEntity=new CommentEntity();
		BeanUtils.copyProperties(comment, commentEntity);
		commentEntity.setPost(postEntity);
       return repository.save(commentEntity).getCommentId();
		
	}

	@Override
	public void deleteComment(Integer id) {
		
	repository.findById(id).orElseThrow(()->new ResourceNotFoundException("Comment not found with id:"+id));
	repository.deleteById(id);
	}

	@Override
	public List<Comment> getAllCommentsByPost(Integer id) {
		PostEntity postEntity=postRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Post Not Found with id:"+id));	
		List<CommentEntity> list=repository.findByPost(postEntity);
		
		List<Comment> comments=new ArrayList<>();
		for(CommentEntity c:list) {
			Comment comment=new Comment();
			comment.setComment(c.getComment());
			comment.setCommentId(c.getCommentId());
			comment.setName(c.getName());
			comment.setEmail(c.getEmail());
			comments.add(comment);
		}
		return comments;
	}

}
