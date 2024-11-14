package com.app.blog.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;


@Data
@Entity
@Table(name = "post_table")
public class PostEntity {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer postId;
	
	
	@Column(name = "title")
	private String postTitle;
	
	@Column(name = "desc_col")
	private String description;
	
	
	@Lob
	@Column(name = "content")
	private String content;
	
	
	@Column(name = "image_name")
	private String imageName;
	
	
	@CreationTimestamp
	@Column(name = "create_date")
	private LocalDate createdDate;
	
	
	@UpdateTimestamp
	@Column(name = "update_date")
	private LocalDate updatedDate;
	
	@ManyToOne
	private CategoryEntity category;
	
	
	@ManyToOne
	private UserEntity user;
	
	@OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
	private List<CommentEntity> comments=new ArrayList<>();
	
}
