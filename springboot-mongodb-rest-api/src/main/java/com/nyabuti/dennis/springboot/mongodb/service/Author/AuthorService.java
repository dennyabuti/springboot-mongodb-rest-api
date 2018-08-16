package com.nyabuti.dennis.springboot.mongodb.service.Author;

import java.util.List;

import com.nyabuti.dennis.springboot.mongodb.model.Author;

public interface AuthorService {
	Author createAuthor(Author author);
	List<Author> getAllAuthors();
	Author findById(String id);
	Author patchAuthor(String id, Author author);
	void deleteAuthor(String id);
}
