package com.app.blog.request;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Lob;
import lombok.Data;


@Data
public class Post {

	private Integer postId;
	
	private String postTitle;

	private String description;

	@Lob
	private String content;

	private Category category;
	
	private User user;
	
	private List<Comment> comments=new ArrayList<>();
	
	private LocalDate createdDate;

	
}
