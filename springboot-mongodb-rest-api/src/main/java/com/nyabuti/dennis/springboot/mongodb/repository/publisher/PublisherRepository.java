package com.nyabuti.dennis.springboot.mongodb.repository.publisher;

import java.util.List;

import com.mongodb.client.result.DeleteResult;
import com.nyabuti.dennis.springboot.mongodb.model.Book;
import com.nyabuti.dennis.springboot.mongodb.model.Publisher;

public interface PublisherRepository {
	Publisher createPublisher(Publisher publisher);
	List<Publisher> getAllPublishers();
	Publisher findById(String id);
	Publisher patchPablisher(Publisher publisher);
	DeleteResult deletePublisher(String id);
	
	
	List<Book> getPublisherBooks(String id); // publisher id

}
