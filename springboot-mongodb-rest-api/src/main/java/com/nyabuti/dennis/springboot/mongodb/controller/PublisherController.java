package com.nyabuti.dennis.springboot.mongodb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mongodb.client.result.DeleteResult;
import com.nyabuti.dennis.springboot.mongodb.model.Book;
import com.nyabuti.dennis.springboot.mongodb.model.Publisher;
import com.nyabuti.dennis.springboot.mongodb.repository.publisher.PublisherRepository;

@RestController
@RequestMapping("/publishers")
public class PublisherController {
	
	@Autowired
	private PublisherRepository publisherRepository;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public List<Publisher> getAllPublisher() {
		return publisherRepository.getAllPublishers();
	}
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public Publisher addPublisher(@Validated @RequestBody Publisher publisher){
		return publisherRepository.createPublisher(publisher);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Publisher findById(@PathVariable("id") String id) {
		return publisherRepository.findById(id);
	}
	
	@RequestMapping(value="/", method = RequestMethod.PATCH)
	public Publisher patchPublisher(@Validated @RequestBody Publisher publisher) {
		return publisherRepository.patchPablisher(publisher);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public DeleteResult deletePublisher(@PathVariable("id") String id) {
		return publisherRepository.deletePublisher(id);
	}
	
	// Relationships
	@RequestMapping(value = "/{id}/books", method = RequestMethod.GET)
	public List<Book> getpublisherBooks(@PathVariable("id") String id) {
		return publisherRepository.getPublisherBooks(id);
	}
	

}
