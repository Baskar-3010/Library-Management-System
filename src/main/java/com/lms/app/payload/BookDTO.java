package com.lms.app.payload;

import org.springframework.web.multipart.MultipartFile;

import com.lms.app.entities.Author;
import com.lms.app.entities.Category;
import com.lms.app.entities.FileDB;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO {
	private String name;

	private long isbn;

	private String description;

	private Category category;

	private FileDB fileDB;
	
	private String url;
	
	private Author author;
}
