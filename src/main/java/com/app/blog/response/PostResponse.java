package com.app.blog.response;

import java.util.List;

import com.app.blog.request.Comment;
import com.app.blog.request.Post;

import lombok.Data;


@Data
public class PostResponse {

	
	private List<Post> content;
	
	private Integer pageNumber;
	
	private Integer pageSize;
	
	private Long totalElements;
	
	private Integer totalPages;
	
	private boolean lastPage;
	
	private List<Comment> comments;
}
