package com.nyabuti.dennis.springboot.mongodb.repository.book;

import java.util.List;

import com.mongodb.client.result.DeleteResult;
import com.nyabuti.dennis.springboot.mongodb.model.Book;

public interface BookRepository {
	Book createBook(Book book);
	List<Book> getAllBooks();
	Book findById(String id);
	List<Book> findByPublisherId(String id);
	Book patchBook(Book book);
	DeleteResult deleteBook(String id);
	// TODO add model relations
}
