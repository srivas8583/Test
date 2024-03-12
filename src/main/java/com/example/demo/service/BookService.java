package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.Utility.CsvUtil;
import com.example.demo.Utility.JwtUtil;
import com.example.demo.model.Book;

@Service
public class BookService {

	public  List<Book> getBooks(String token){
		String userType= JwtUtil.getUserTypeFromToken(token);
		if("admin".equals(userType)) {
			List<Book> adminBooks=CsvUtil.readBooksFromCSV("adminUser.csv");
			List<Book> regularBooks=CsvUtil.readBooksFromCSV("regularUser.csv");
			adminBooks.addAll(regularBooks);
			return adminBooks;
		}
		
		else if("regular".equals(userType)) {
			return CsvUtil.readBooksFromCSV("regular.csv");
		}else {
			throw new IllegalArgumentException("Invalid user type");
		}
	}
	
	public void addBook(String token, Book book) {
		String userType=JwtUtil.getUserTypeFromToken(token);
		if("admin".equals(userType)) {
			CsvUtil.addBookToCSV("regularUser.csv", book);
		}else {
			throw new IllegalArgumentException("Access denied. Only admin users can add books.");
		}
	}
	public void deleteBook(String token,String bookName) {
		String userType=JwtUtil.getUserTypeFromToken(token);
		if("admin".equals(userType)) {
			CsvUtil.deleteBookFromCSV("regularUser.csv",bookName);
		}else {
			throw new IllegalArgumentException("Access denied. Only admin users can delete books");
		}
	}
}
