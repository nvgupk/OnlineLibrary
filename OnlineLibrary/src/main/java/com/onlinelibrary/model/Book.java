package com.onlinelibrary.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="book")
@Cacheable(true)
public class Book implements Serializable {
	private Long bookId;
	private String name;
	private String description;
	private String booksFilePath;
	private String coverImgPath;
	private List<Author> authors = new ArrayList<Author>();
	private Set<Genre> genres = new HashSet<Genre>();
	
	public Book() {
	}
	
	@Id
	@Column(name = "book_id", nullable = false, unique = true)
	@SequenceGenerator(name="book_book_id_seq",
		sequenceName="book_book_id_seq",
		allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,
		generator="book_book_id_seq")
	public Long getBookId() {
		return bookId;
	}

	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}
	
	@Column(name = "name", nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name = "description")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@Column(name = "books_file_path", nullable = false)
	public String getBooksFilePath() {
		return booksFilePath;
	}

	public void setBooksFilePath(String booksFilePath) {
		this.booksFilePath = booksFilePath;
	}
	
	@Column(name = "cover_img_path")
	public String getCoverImgPath() {
		return coverImgPath;
	}

	public void setCoverImgPath(String coverImgPath) {
		this.coverImgPath = coverImgPath;
	}
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(name = "book_author", joinColumns = {
			@JoinColumn(name = "book_id", nullable = false)},
			inverseJoinColumns = {
				@JoinColumn(name = "author_id", nullable = false)})
	public List<Author> getAuthors() {
		return authors;
	}

	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(name = "book_genre", joinColumns = {
			@JoinColumn(name = "book_id", nullable = false)},
			inverseJoinColumns = {
				@JoinColumn(name = "genre_id", nullable = false)})
	public Set<Genre> getGenres() {
		return genres;
	}

	public void setGenres(Set<Genre> genres) {
		this.genres = genres;
	}
}
