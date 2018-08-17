package com.nyabuti.dennis.springboot.mongodb.service.book;

import java.util.List;

import com.mongodb.client.result.DeleteResult;
import com.nyabuti.dennis.springboot.mongodb.model.Book;

public interface BookService {
	Book createBook(Book book);
	List<Book> getAllBooks();
	Book findById(String id);
	Book patchBook(Book book);
	DeleteResult deleteBook(String id);
	// TODO add model relations
}
