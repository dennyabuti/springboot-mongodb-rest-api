package com.nyabuti.dennis.springboot.mongodb.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mongodb.client.result.DeleteResult;
import com.nyabuti.dennis.springboot.mongodb.model.Author;
import com.nyabuti.dennis.springboot.mongodb.model.Book;
import com.nyabuti.dennis.springboot.mongodb.model.BookAuthor;
import com.nyabuti.dennis.springboot.mongodb.repository.author.AuthorRepository;
import com.nyabuti.dennis.springboot.mongodb.repository.book.BookRepository;
import com.nyabuti.dennis.springboot.mongodb.repository.book_author.BookAuthorRepository;

@RestController
@RequestMapping("/authors")
public class AuthorController {
	@Autowired
	private AuthorRepository authorRepository;
	@Autowired
	private BookAuthorRepository bookAuthorRepository;
	@Autowired
	private BookRepository bookRepository;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	List<Author> getAllAuthor() {
		return authorRepository.getAllAuthors();
	}
	
	@RequestMapping(value="/", method = RequestMethod.POST)
	public Author addAuthor(@Validated @RequestBody Author author) {
		return authorRepository.createAuthor(author);
	}
	
	@RequestMapping(value= "/{id}", method = RequestMethod.GET)
	public Author findById(@PathVariable("id") String id) {
		return authorRepository.findById(id);
	}
	
	@RequestMapping(value = "/{id}/books", method = RequestMethod.GET)
	public List<Book> findBooks(@PathVariable("id") String id){
		List<BookAuthor> bookAuthors = bookAuthorRepository.findByAuthorId(id);
		ArrayList<String> bookIds = new ArrayList<String>();
		for(BookAuthor bk: bookAuthors) {
			bookIds.add(bk.getBookId().toString());
		}
		return bookRepository.findByIds(bookIds);
	}
	
	@RequestMapping(value="/", method = RequestMethod.PATCH)
	public Author patchAuthor(@Validated @RequestBody Author author) {
		// TO DO require id to be provided
		return authorRepository.patchAuthor(author);
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.DELETE)
	public DeleteResult deleteCount(@PathVariable("id") String id) {
		return authorRepository.deleteAuthor(id);
	}

}
