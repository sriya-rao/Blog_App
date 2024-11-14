package com.app.blog.service;

import java.util.List;

import com.app.blog.request.Comment;

public interface CommentService {

	
	Integer createComment(Comment comment, Integer postId);
	
	void deleteComment(Integer id);
	
	List<Comment> getAllCommentsByPost(Integer id);
}
