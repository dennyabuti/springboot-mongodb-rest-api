package com.nyabuti.dennis.springboot.mongodb.repository.author;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.mongodb.client.result.DeleteResult;
import com.nyabuti.dennis.springboot.mongodb.model.Author;

@Repository
public class AuthorRepositoryImpl implements AuthorRepository {
	private final MongoTemplate mongoTemplete;
	
	public AuthorRepositoryImpl(MongoTemplate mongoTemplate) {
		this.mongoTemplete = mongoTemplate;
	}
	
	@Override
	public Author createAuthor(Author author) {
		author.setId(ObjectId.get().toString());
		mongoTemplete.save(author);
		return author;
	}

	@Override
	public List<Author> getAllAuthors() {
		return mongoTemplete.findAll(Author.class);
	}

	@Override
	public Author findById(String id) {
		return mongoTemplete.findById(id, Author.class);
	}

	@Override
	public Author patchAuthor(Author author) {
		mongoTemplete.save(author);
		return author;
	}

	@Override
	public DeleteResult deleteAuthor(String id) {
		Author author = mongoTemplete.findById(id, Author.class);
		return mongoTemplete.remove(author);
	}

}