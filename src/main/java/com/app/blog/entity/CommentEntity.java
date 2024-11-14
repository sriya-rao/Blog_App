package com.app.blog.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "comment_table")
@Data
public class CommentEntity {

	@Id
	@GeneratedValue
	@Column(name = "comment_id_col")
	private Integer commentId;
		
	@Column(name = "comment_content_col")
	private String comment;
	
	@Column(name = "comment_user_name")
    private String name;
	
	@Column(name = "comment_user_email")
	private String email;

	
	@ManyToOne
	private PostEntity post;
	
	
	
	
}
