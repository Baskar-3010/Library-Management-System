package com.lms.app.payload;

import java.util.List;

import com.lms.app.entities.Book;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor

public class CategoryDTO {
	private String name;

	private String description;

	private List<Book> books;
}
