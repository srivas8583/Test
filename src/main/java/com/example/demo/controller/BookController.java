package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Book;
import com.example.demo.service.BookService;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/book")
public class BookController {

	@Autowired
	private  BookService bookService;

	 @Autowired
	    public void setBookService(BookService bookService) {
	        this.bookService = bookService;
	    }
	 
	 
    @GetMapping("/home")
    public ResponseEntity<?> getBooks(@RequestHeader("Authorization") String token) {
        try {
            List<Book> books = bookService.getBooks(token);
            return ResponseEntity.ok(books);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(403).body("Access denied");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Internal Server Error");
        }
    }
	
	@PostMapping("/addBook")
	public ResponseEntity<String> addBook(@RequestHeader("Authorization")String token,@RequestBody Book book){
		try {
			bookService.addBook(token, book);
			return ResponseEntity.ok("Book added sucessfully");
		}catch(IllegalArgumentException e) {
			return ResponseEntity.status(403).body(e.getMessage());
		}
	}
	@DeleteMapping("/deleteBook")
	public ResponseEntity<String> deleteBook(@RequestHeader("Authorization")String token,@RequestParam String bookName){
		try {
			bookService.deleteBook(token, bookName);
			return ResponseEntity.ok("Book deleted sucessfully");
		}catch(IllegalArgumentException e) {
			return ResponseEntity.status(403).body(e.getMessage());
		}
	}
}
	
