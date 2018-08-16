package com.nyabuti.dennis.springboot.mongodb.service.Author;

import java.util.List;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.nyabuti.dennis.springboot.mongodb.model.Author;

@Repository
public class AuthorServiceImpl implements AuthorService {
	private final MongoTemplate mongoTemplete;
	
	public AuthorServiceImpl(MongoTemplate mongoTemplate) {
		this.mongoTemplete = mongoTemplate;
	}
	
	@Override
	public Author createAuthor(Author author) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Author> getAllAuthors() {
		// TODO Auto-generated method stub
		return mongoTemplete.findAll(Author.class);
	}

	@Override
	public Author findById(String id) {
		return mongoTemplete.findById(id, Author.class);
	}

	@Override
	public Author patchAuthor(String id, Author author) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteAuthor(String id) {
		
	}

}
