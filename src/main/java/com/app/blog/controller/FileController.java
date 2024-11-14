package com.app.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.app.blog.service.FileService;

@RestController
@RequestMapping("/file")
public class FileController {

	@Autowired
	FileService service;
	
	
	@PostMapping("/save")
	public ResponseEntity<String> fileSave(@RequestParam MultipartFile file) throws Exception{
		
	 String path="/images";
	  String name=service.uploadImage(path, file);
		return new ResponseEntity<String>("FileName"+name, HttpStatus.OK);
	}
}
