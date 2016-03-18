package com.onlinelibrary.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "author")
public class Author implements Serializable {
	private Long authorId;
	private String name;
	private List<Book> books = new ArrayList<Book>();
	
	public Author() {
	}
	
	public Author(Long authorId, String name) {
		this.authorId = authorId;
		this.name = name;
	}
	
	public Author(String name) {
		this.name = name;
	}

	@Id
	@Column(name = "author_id", nullable = false, unique = true)
	@SequenceGenerator(name="author_author_id_seq",
		sequenceName="author_author_id_seq",
		allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,
		generator="author_author_id_seq")
	public Long getAuthorId() {
		return authorId;
	}

	public void setAuthorId(Long authorId) {
		this.authorId = authorId;
	}
	
	@Column(name = "name", nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "authors", 
			cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}
	
}
