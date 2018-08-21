package com.nyabuti.dennis.springboot.mongodb.repository.book_author;

import com.nyabuti.dennis.springboot.mongodb.model.BookAuthor;

public interface BookAuthorRepository {
	BookAuthor findById(String id);
	BookAuthor createBookAuthor(BookAuthor bookAuthor);
	BookAuthor patchBookAuthor(BookAuthor bookAuthor);
	
}
