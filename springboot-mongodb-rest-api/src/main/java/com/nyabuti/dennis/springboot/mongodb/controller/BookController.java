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
import com.nyabuti.dennis.springboot.mongodb.model.Publisher;
import com.nyabuti.dennis.springboot.mongodb.repository.book.BookRepository;
import com.nyabuti.dennis.springboot.mongodb.repository.publisher.PublisherRepository;

@RestController
@RequestMapping("/books")
public class BookController {
	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private PublisherRepository publisherRepository;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	List<Book> getAllBooks() {
		return bookRepository.getAllBooks();
	}
	
	@RequestMapping(value="/", method = RequestMethod.POST)
	public Book addBook(@Validated @RequestBody Book book) {
		return bookRepository.createBook(book);
	}
	
	@RequestMapping(value= "/{id}", method = RequestMethod.GET)
	public Book findById(@PathVariable("id") String id) {
		return bookRepository.findById(id);
	}
	
	@RequestMapping(value = "/{id}/publisher", method = RequestMethod.GET)
	Publisher getPublisher(@PathVariable("id") String id) {
		Book book = bookRepository.findById(id);
		return publisherRepository.findById(book.getPublisherId());
	}
	
	@RequestMapping(value= "/find-by-publisher/{publisherId}", method = RequestMethod.GET)
	public List<Book> findbyPublisher(@PathVariable("publisherId") String publisherId) {
		return bookRepository.findByPublisherId(publisherId);
	}
	
	@RequestMapping(value="/", method = RequestMethod.PATCH)
	public Book patchBook(@Validated @RequestBody Book book) {
		// TO DO require id to be provided
		return bookRepository.patchBook(book);
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.DELETE)
	public DeleteResult deleteBook(@PathVariable("id") String id) {
		return bookRepository.deleteBook(id);
	}
}