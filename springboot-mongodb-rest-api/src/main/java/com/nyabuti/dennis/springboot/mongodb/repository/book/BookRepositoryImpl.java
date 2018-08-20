package com.nyabuti.dennis.springboot.mongodb.repository.book;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.mongodb.client.result.DeleteResult;
import com.nyabuti.dennis.springboot.mongodb.model.Book;

@Repository
public class BookRepositoryImpl implements BookRepository {
	private final MongoTemplate mongoTemplete;
	private Query query;
	public BookRepositoryImpl(MongoTemplate mongoTemplete) {
		this.mongoTemplete = mongoTemplete;
	}
	@Override
	public Book createBook(Book book) {
		book.setId(ObjectId.get().toString());
		mongoTemplete.save(book);
		return book;
	}

	@Override
	public List<Book> getAllBooks() {
		return mongoTemplete.findAll(Book.class);
	}

	@Override
	public Book findById(String id) {
		return mongoTemplete.findById(id, Book.class);
	}

	@Override
	public Book patchBook(Book book) {
		mongoTemplete.save(book);
		return book;
	}

	@Override
	public DeleteResult deleteBook(String id) {
		Book book = mongoTemplete.findById(id, Book.class);
		DeleteResult deleteResult = mongoTemplete.remove(book);
		return deleteResult;
	}
	@Override
	public List<Book> findByPublisherId(String publisherId) {
		query = Query.query(Criteria.where("publisherId").is(new ObjectId(publisherId)));
//		mongoTemplete.find
		return mongoTemplete.find(query, Book.class);
	}

}
