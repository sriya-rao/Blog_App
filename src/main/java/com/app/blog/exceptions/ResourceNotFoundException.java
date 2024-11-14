package com.app.blog.exceptions;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ResourceNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	String message;

	public ResourceNotFoundException(String message) {
		super();
		this.message = message;
	}
	
	
		
	

}
