package com.app.blog.request;

import lombok.Data;

@Data
public class Comment {
	
	private Integer commentId;
	
	private String name;
	
	private String email;

	private String comment;
	

}
