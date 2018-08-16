package com.nyabuti.dennis.springboot.mongodb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

}
