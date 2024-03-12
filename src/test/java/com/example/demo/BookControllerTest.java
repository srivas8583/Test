package com.example.demo;


import static org.mockito.Mockito.*;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.demo.controller.BookController;
import com.example.demo.model.Book;
import com.example.demo.service.BookService;

class BookControllerTest {

    @Test
    void testGetBooks_Success() {
        BookService bookService = mock(BookService.class);
        BookController bookController = new BookController();
        bookController.setBookService(bookService);

        String token = "validToken";
        when(bookService.getBooks(token)).thenReturn(Collections.singletonList(new Book("Book1", "Author1", 2022)));

        ResponseEntity<?> response = bookController.getBooks(token);

        assert response.getStatusCode() == HttpStatus.OK;
        assert response.getBody() instanceof List;
    }

    @Test
    void testGetBooks_Failure() {
        BookService bookService = mock(BookService.class);
        BookController bookController = new BookController();
        bookController.setBookService(bookService);

        String token = "invalidToken";
        when(bookService.getBooks(token)).thenThrow(new IllegalArgumentException("Access denied"));

        ResponseEntity<?> response = bookController.getBooks(token);

        assert response.getStatusCode() == HttpStatus.FORBIDDEN;
        assert response.getBody().equals("Access denied");
    }

    @Test
    void testAddBook_Success() {
        BookService bookService = mock(BookService.class);
        BookController bookController = new BookController();
        bookController.setBookService(bookService);

        String token = "validToken";
        Book book = new Book("NewBook", "NewAuthor", 2023);

        ResponseEntity<?> response = bookController.addBook(token, book);

        assert response.getStatusCode() == HttpStatus.OK;
        assert response.getBody().equals("Book added successfully");
    }

    @Test
    void testAddBook_Failure() {
        BookService bookService = mock(BookService.class);
        BookController bookController = new BookController();

        String token = "invalidToken";
        Book book = new Book("NewBook", "NewAuthor", 2023);

        // Use doThrow() for void methods
        doThrow(new IllegalArgumentException("Access denied")).when(bookService).addBook(token, book);

        ResponseEntity<?> response = bookController.addBook(token, book);

        assert response.getStatusCode() == HttpStatus.FORBIDDEN;
        assert response.getBody().equals("Access denied");
    }

    @Test
    void testDeleteBook_Success() {
        BookService bookService = mock(BookService.class);
        BookController bookController = new BookController();

        String token = "validToken";
        String bookName = "BookToDelete";

        // Use doNothing() for void methods
        doNothing().when(bookService).deleteBook(token, bookName);

        ResponseEntity<?> response = bookController.deleteBook(token, bookName);

        assert response.getStatusCode() == HttpStatus.OK;
        assert response.getBody().equals("Book deleted successfully");
    }

    @Test
    void testDeleteBook_Failure() {
    	 BookService bookService = mock(BookService.class);
    	    BookController bookController = new BookController();

        String token = "invalidToken";
        String bookName = "BookToDelete";

        // Use doThrow() for void methods
        doThrow(new IllegalArgumentException("Access denied")).when(bookService).deleteBook(token, bookName);

        ResponseEntity<?> response = bookController.deleteBook(token, bookName);

        assert response.getStatusCode() == HttpStatus.FORBIDDEN;
        assert response.getBody().equals("Access denied");
    }

}

