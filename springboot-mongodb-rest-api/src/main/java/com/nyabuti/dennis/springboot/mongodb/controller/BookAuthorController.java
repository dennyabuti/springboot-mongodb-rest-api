package com.nyabuti.dennis.springboot.mongodb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mongodb.client.result.DeleteResult;
import com.nyabuti.dennis.springboot.mongodb.model.BookAuthor;
import com.nyabuti.dennis.springboot.mongodb.repository.book_author.BookAuthorRepository;

@RestController
@RequestMapping("/book-author")
public class BookAuthorController {
	@Autowired
	private BookAuthorRepository bookAuthorRepository;
	
	@RequestMapping(value="/", method = RequestMethod.POST)
	public BookAuthor addBookAuthor(@Validated @RequestBody BookAuthor bookAuthor) {
		return bookAuthorRepository.createBookAuthor(bookAuthor);
	}
	
	@RequestMapping(value="/", method = RequestMethod.DELETE)
	public DeleteResult deleteCount(@PathVariable("id") String id) {
		return bookAuthorRepository.deleteBookAuthor(id);
	}

}
