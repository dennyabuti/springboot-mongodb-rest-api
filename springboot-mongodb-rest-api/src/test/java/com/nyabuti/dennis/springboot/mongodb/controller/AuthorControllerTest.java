package com.nyabuti.dennis.springboot.mongodb.controller;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.mongodb.client.result.DeleteResult;
import com.nyabuti.dennis.springboot.mongodb.model.Author;
import com.nyabuti.dennis.springboot.mongodb.model.Book;
import com.nyabuti.dennis.springboot.mongodb.model.BookAuthor;
import com.nyabuti.dennis.springboot.mongodb.repository.author.AuthorRepository;
import com.nyabuti.dennis.springboot.mongodb.repository.book.BookRepository;
import com.nyabuti.dennis.springboot.mongodb.repository.book_author.BookAuthorRepository;

@RunWith(SpringRunner.class)
@WebMvcTest(value = AuthorController.class, secure = false)
public class AuthorControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private AuthorRepository authorRepository;
	@MockBean
	private BookAuthorRepository bookAuthorRepository;
	@MockBean
	private BookRepository bookRepository;

	private List<Author> authors = new ArrayList<>();
	private List<Book> books = new ArrayList<>();
	private String exampleSaveAuthor = "{\"firstName\": \"John\", \"lastName\": \"Doe\"}";
	private String exampleUpdateAuthor = "{\"id\": \"1\", \"firstName\": \"John\", \"lastName\": \"Doe\"}";

	@Before
	public void setup() throws Exception {
		Author author = new Author();
		author.setFirstName("John");
		author.setLastName("Doe");
		author.setId("1");
		Author author1 = new Author();
		author1.setFirstName("Jane");
		author1.setLastName("Dee");
		author1.setId("2");
		this.authors.add(author);
		this.authors.add(author1);
	}

	@Test // get Author by id
	public void getAuthorByIdTest() throws Exception {
		Mockito.when(authorRepository.findById(Mockito.anyString())).thenReturn(this.authors.get(0));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/authors/1/").accept(MediaType.APPLICATION_JSON);

		mockMvc.perform(requestBuilder).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$.id", equalTo("1"))).andExpect(jsonPath("$.firstName", equalTo("John")))
				.andExpect(jsonPath("$.lastName", equalTo("Doe")));

	}

	@Test // get all Authors
	public void getAllAuthorsTest() throws Exception {
		Mockito.when(authorRepository.getAllAuthors()).thenReturn(this.authors);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/authors/").accept(MediaType.APPLICATION_JSON);

		mockMvc.perform(requestBuilder).andExpect(status().isOk())
				.andExpect(jsonPath("$", org.hamcrest.Matchers.hasSize(2)));
	}

	//
	@Test // create Author
	public void createAuthorTest() throws Exception {
		Mockito.when(authorRepository.createAuthor(Mockito.<Author>any())).thenReturn(this.authors.get(1));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/authors/").accept(MediaType.APPLICATION_JSON)
				.content(exampleSaveAuthor).contentType(MediaType.APPLICATION_JSON);
		mockMvc.perform(requestBuilder).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8)).andExpect(jsonPath("id").value("2"))
				.andExpect(jsonPath("firstName").value("Jane")).andExpect(jsonPath("lastName").value("Dee"));

	}

	//
	@Test // update Author
	public void updateAuthorTest() throws Exception {
		Mockito.when(authorRepository.patchAuthor(Mockito.<Author>any())).thenReturn(this.authors.get(0));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.patch("/authors/").accept(MediaType.APPLICATION_JSON)
				.content(exampleUpdateAuthor).contentType(MediaType.APPLICATION_JSON);
		mockMvc.perform(requestBuilder).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("firstName").value("John")).andExpect(jsonPath("lastName").value("Doe"));
	}

	@Test // books by author
	public void fetchBooksByAuthor() throws Exception {
		BookAuthor ba = new BookAuthor();
		ba.setAuthorId(ObjectId.get());
		ba.setBookId(ObjectId.get());
		ba.setId("1");
		List<BookAuthor> bookAuthors = new ArrayList<>();
		bookAuthors.add(ba);
		Book book = new Book();
		book.setId("1");
		book.setTitle("Test Book");
		book.setLanguage("English");
		book.setPages(8);
		book.setPublisherId(ObjectId.get());
		this.books.add(book);
		Mockito.when(bookAuthorRepository.findByAuthorId(Mockito.anyString())).thenReturn(bookAuthors);
		Mockito.when(bookRepository.findByIds(Mockito.<String>anyList())).thenReturn(this.books);

		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get("/authors/" + ba.getAuthorId().toString() + "/books/").accept(MediaType.APPLICATION_JSON);

		mockMvc.perform(requestBuilder).andExpect(status().isOk()).andDo(print())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$", org.hamcrest.Matchers.hasSize(1)))
				.andExpect(jsonPath("$[0].title", equalTo("Test Book")))
				.andExpect(jsonPath("$[0].language", equalTo("English"))).andExpect(jsonPath("$[0].id", equalTo("1")))
				.andExpect(jsonPath("$[0].publisherId", equalTo(this.books.get(0).getPublisherId())))
				.andExpect(jsonPath("$[0].pages", equalTo(8)));
	}

	//
	@Test // remove author
	public void removeAuthorTest() throws Exception {
		Mockito.when(bookAuthorRepository.deleteByAuthorId(Mockito.anyString())).thenReturn(DeleteResult.acknowledged(1));
//		Mockito.verify(bookAuthorRepository.deleteByAuthorId(Mockito.anyString()), times(1));
		Mockito.when(authorRepository.deleteAuthor(Mockito.anyString())).thenReturn(DeleteResult.acknowledged(1));

		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/authors/1").accept(MediaType.APPLICATION_JSON);
		
		mockMvc.perform(requestBuilder)
			.andDo(print())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.deletedCount", equalTo(1)));
	}

}
