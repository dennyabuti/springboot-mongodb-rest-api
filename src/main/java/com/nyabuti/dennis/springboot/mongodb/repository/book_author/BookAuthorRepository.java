package com.nyabuti.dennis.springboot.mongodb.repository.book_author;

import java.util.List;

import com.mongodb.client.result.DeleteResult;
import com.nyabuti.dennis.springboot.mongodb.model.BookAuthor;

public interface BookAuthorRepository {
	List<BookAuthor> findAll();
	BookAuthor findById(String id);
	BookAuthor createBookAuthor(BookAuthor bookAuthor);
	BookAuthor patchBookAuthor(BookAuthor bookAuthor);
	DeleteResult deleteBookAuthor(String id);
	List<BookAuthor> findByAuthorId(String authorId);
	List<BookAuthor> findByBookId(String bookId);
	DeleteResult deleteByAuthorId(String AuthorId);
	DeleteResult deleteByBookId(String bookId);
}
