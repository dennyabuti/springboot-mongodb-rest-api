package com.nyabuti.dennis.springboot.mongodb.repository.author;

import java.util.List;

import com.mongodb.client.result.DeleteResult;
import com.nyabuti.dennis.springboot.mongodb.model.Author;

public interface AuthorRepository {
	Author createAuthor(Author author);
	List<Author> getAllAuthors();
	Author findById(String id);
	List<Author> findByIds(List<String> ids);
	Author patchAuthor(Author author);
	DeleteResult deleteAuthor(String id);
	// TODO add model relations
}
