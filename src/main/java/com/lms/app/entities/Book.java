package com.lms.app.entities;



import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name ="books")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	private long isbn;
	
	private String description;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="file_id")
	private FileDB fileDB;
	
	@ManyToOne(cascade = {CascadeType.PERSIST , CascadeType.MERGE , CascadeType.REFRESH , CascadeType.DETACH})
	@JoinColumn(name="category_id")
	@JsonIgnore
	private Category category;

	
	@ManyToOne(cascade = {CascadeType.PERSIST , CascadeType.MERGE , CascadeType.REFRESH , CascadeType.DETACH})
	@JoinColumn(name="author_id")
	@JsonIgnore
	private Author author;
}
