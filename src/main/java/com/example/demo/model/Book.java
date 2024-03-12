package com.example.demo.model;

public class Book {

	private String bookName;
	private String author;
	private int publicationYear;
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public int getPublicationYear() {
		return publicationYear;
	}
	public void setPublicationYear(int publicationYear) {
		this.publicationYear = publicationYear;
	}
	public Book(String bookName, String author, int publicationYear) {
		super();
		this.bookName = bookName;
		this.author = author;
		this.publicationYear = publicationYear;
	}
	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
