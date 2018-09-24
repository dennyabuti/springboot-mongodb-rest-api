package com.nyabuti.dennis.springboot.mongodb.repository.publisher;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.mongodb.client.result.DeleteResult;
import com.nyabuti.dennis.springboot.mongodb.model.Book;
import com.nyabuti.dennis.springboot.mongodb.model.Publisher;
import com.nyabuti.dennis.springboot.mongodb.repository.book.BookRepository;

@Repository
public class PublisherRepositoryImpl implements PublisherRepository {
	
	private final MongoTemplate mongoTemplete;
	private BookRepository bookRepository;
	
	public PublisherRepositoryImpl(MongoTemplate mongoTemplate, BookRepository bookRepository) {
		this.mongoTemplete = mongoTemplate;
		this.bookRepository = bookRepository;
	}
	

	@Override
	public Publisher createPublisher(Publisher publisher) {
		publisher.setId(ObjectId.get().toString());
		mongoTemplete.save(publisher);
		return publisher;
	}

	@Override
	public List<Publisher> getAllPublishers() {
		return mongoTemplete.findAll(Publisher.class);
	}

	@Override
	public Publisher findById(String id) {
		return mongoTemplete.findById(id, Publisher.class);
	}

	@Override
	public Publisher patchPablisher(Publisher publisher) {
		mongoTemplete.save(publisher);
		return publisher;
	}

	@Override
	public DeleteResult deletePublisher(String id) {
		Publisher publisher = mongoTemplete.findById(id, Publisher.class);
		return mongoTemplete.remove(publisher);
	}

	@Override
	public List<Book> getPublisherBooks(String id) {
		System.out.println(id);
		return bookRepository.findByPublisherId(id); 
	}

}
