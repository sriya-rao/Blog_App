package com.app.blog.service.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.app.blog.service.FileService;


@Service
public class FileServiceImpl implements FileService{

	@Override
	public String uploadImage(String path, MultipartFile file) throws Exception {
       
		String name=file.getOriginalFilename();
		
		String filePath=path+File.separator+name;
		
		File f=new File(path);
		if(!f.exists()) {
			f.mkdir();
		}
		
		Files.copy(file.getInputStream(), Paths.get(filePath));
		
		return name;
	}

	@Override
	public InputStream getResource(String path, String fileName) throws FileNotFoundException {

		return null;
	}

	
	
	
	
}
