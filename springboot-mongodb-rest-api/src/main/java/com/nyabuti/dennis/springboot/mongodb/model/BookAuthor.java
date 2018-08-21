package com.nyabuti.dennis.springboot.mongodb.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "book-author")
public class BookAuthor {
	@Id
	private String id;
	private ObjectId authorId;
	private ObjectId bookId;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public ObjectId getAuthorId() {
		return authorId;
	}
	public void setAuthorId(ObjectId authorId) {
		this.authorId = authorId;
	}
	public ObjectId getBookId() {
		return bookId;
	}
	public void setBookId(ObjectId bookId) {
		this.bookId = bookId;
	}
}
