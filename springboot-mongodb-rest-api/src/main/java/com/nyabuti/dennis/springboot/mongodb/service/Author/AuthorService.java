package com.nyabuti.dennis.springboot.mongodb.service.Author;

import java.util.List;

import com.mongodb.client.result.DeleteResult;
import com.nyabuti.dennis.springboot.mongodb.model.Author;

public interface AuthorService {
	Author createAuthor(Author author);
	List<Author> getAllAuthors();
	Author findById(String id);
	Author patchAuthor(Author author);
	DeleteResult deleteAuthor(String id);
}
