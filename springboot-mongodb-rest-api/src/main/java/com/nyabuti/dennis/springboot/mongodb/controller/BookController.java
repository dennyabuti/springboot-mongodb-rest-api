package com.nyabuti.dennis.springboot.mongodb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mongodb.client.result.DeleteResult;
import com.nyabuti.dennis.springboot.mongodb.model.Book;
import com.nyabuti.dennis.springboot.mongodb.service.book.BookService;

@RestController
@RequestMapping("/books")
public class BookController {
	@Autowired
	private BookService bookService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	List<Book> getAllBooks() {
		return bookService.getAllBooks();
	}
	
	@RequestMapping(value="/", method = RequestMethod.POST)
	public Book addBook(@Validated @RequestBody Book book) {
		return bookService.createBook(book);
	}
	
	@RequestMapping(value= "/{id}", method = RequestMethod.GET)
	public Book findById(@PathVariable("id") String id) {
		return bookService.findById(id);
	}
	
	@RequestMapping(value="/", method = RequestMethod.PATCH)
	public Book patchBook(@Validated @RequestBody Book book) {
		// TO DO require id to be provided
		return bookService.patchBook(book);
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.DELETE)
	public DeleteResult deleteCount(@PathVariable("id") String id) {
		return bookService.deleteBook(id);
	}
}
