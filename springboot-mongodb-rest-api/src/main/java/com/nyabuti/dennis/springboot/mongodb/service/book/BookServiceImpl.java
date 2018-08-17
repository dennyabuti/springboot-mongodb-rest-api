package com.nyabuti.dennis.springboot.mongodb.service.book;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.mongodb.client.result.DeleteResult;
import com.nyabuti.dennis.springboot.mongodb.model.Book;

@Repository
public class BookServiceImpl implements BookService {
	private final MongoTemplate mongoTemplete;
	public BookServiceImpl(MongoTemplate mongoTemplete) {
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

}
