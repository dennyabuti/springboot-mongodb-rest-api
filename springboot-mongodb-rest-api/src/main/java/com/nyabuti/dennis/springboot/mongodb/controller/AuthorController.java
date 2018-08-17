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
import com.nyabuti.dennis.springboot.mongodb.model.Author;
import com.nyabuti.dennis.springboot.mongodb.service.Author.AuthorService;

@RestController
@RequestMapping("/authors")
public class AuthorController {
	@Autowired
	private AuthorService authorService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	List<Author> getAllAuthor() {
		return authorService.getAllAuthors();
	}
	
	@RequestMapping(value="/", method = RequestMethod.POST)
	public Author addAuthor(@Validated @RequestBody Author author) {
		return authorService.createAuthor(author);
	}
	
	@RequestMapping(value= "/{id}", method = RequestMethod.GET)
	public Author findById(@PathVariable("id") String id) {
		return authorService.findById(id);
	}
	
	@RequestMapping(value="/", method = RequestMethod.PATCH)
	public Author patchAuthor(@Validated @RequestBody Author author) {
		// TO DO require id to be provided
		return authorService.patchAuthor(author);
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.DELETE)
	public DeleteResult deleteCount(@PathVariable("id") String id) {
		return authorService.deleteAuthor(id);
	}

}
