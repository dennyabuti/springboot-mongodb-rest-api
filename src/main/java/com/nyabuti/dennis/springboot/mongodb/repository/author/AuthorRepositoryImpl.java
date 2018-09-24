package com.nyabuti.dennis.springboot.mongodb.repository.author;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.mongodb.client.result.DeleteResult;
import com.nyabuti.dennis.springboot.mongodb.model.Author;

@Repository
public class AuthorRepositoryImpl implements AuthorRepository {
	private final MongoTemplate mongoTemplete;
	private Query query;
	
	public AuthorRepositoryImpl(MongoTemplate mongoTemplate) {
		this.mongoTemplete = mongoTemplate;
	}
	
	@Override
	public Author createAuthor(Author author) {
		author.setId(ObjectId.get().toString());
		mongoTemplete.save(author);
		return author;
	}

	@Override
	public List<Author> getAllAuthors() {
		return mongoTemplete.findAll(Author.class);
	}

	@Override
	public Author findById(String id) {
		return mongoTemplete.findById(id, Author.class);
	}

	@Override
	public Author patchAuthor(Author author) {
		mongoTemplete.save(author);
		return author;
	}

	@Override
	public DeleteResult deleteAuthor(String id) {
		Author author = mongoTemplete.findById(id, Author.class);
		return mongoTemplete.remove(author);
	}

	@Override
	public List<Author> findByIds(List<String> ids) {
		List<ObjectId> objectIds = new ArrayList<ObjectId>();
		for (String id: ids) {
			objectIds.add(new ObjectId(id));
		}
		query = new Query(Criteria.where("_id").in(objectIds));
		return mongoTemplete.find(query, Author.class);
	}

}
