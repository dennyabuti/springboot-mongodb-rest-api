package com.nyabuti.dennis.springboot.mongodb.repository.book_author;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.mongodb.client.result.DeleteResult;
import com.nyabuti.dennis.springboot.mongodb.model.BookAuthor;

@Repository
public class BookAuthorRepositoryImpl implements BookAuthorRepository {
	private final MongoTemplate mongoTemplate;
	private Query query;
	
	public BookAuthorRepositoryImpl(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	@Override
	public BookAuthor findById(String id) {
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

	@Override
	public List<BookAuthor> findAll() {
		return mongoTemplate.findAll(BookAuthor.class);
	}

	@Override
	public DeleteResult deleteBookAuthor(String id) {
		BookAuthor bookAuthor = mongoTemplate.findById(id, BookAuthor.class);
		return mongoTemplate.remove(bookAuthor);
	}

	@Override
	public List<BookAuthor> findByAuthorId(String authorId) {
		query = new Query(Criteria.where("authorId").is(new ObjectId(authorId)));
		return mongoTemplate.find(query, BookAuthor.class);
	}

	@Override
	public List<BookAuthor> findByBookId(String bookId) {
		query = new Query(Criteria.where("bookId").is(new ObjectId(bookId)));
		return mongoTemplate.find(query, BookAuthor.class);
	}

}
