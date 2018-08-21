package com.nyabuti.dennis.springboot.mongodb.repository.book_author;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.nyabuti.dennis.springboot.mongodb.model.BookAuthor;

public class BookAuthorRepositoryImpl implements BookAuthorRepository {
	private final MongoTemplate mongoTemplate;
	
	public BookAuthorRepositoryImpl(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	@Override
	public BookAuthor findById(String id) {
		// TODO Auto-generated method stub
		return mongoTemplate.findById(id, BookAuthor.class);
	}

	@Override
	public BookAuthor createBookAuthor(BookAuthor bookAuthor) {
		bookAuthor.setId(ObjectId.get().toString());
		mongoTemplate.save(bookAuthor);
		return bookAuthor;
	}

	@Override
	public BookAuthor patchBookAuthor(BookAuthor bookAuthor) {
		mongoTemplate.save(bookAuthor);
		return bookAuthor;
	}

}
