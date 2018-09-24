package com.nyabuti.dennis.springboot.mongodb.controller;

import static org.hamcrest.CoreMatchers.equalTo;
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
import com.nyabuti.dennis.springboot.mongodb.repository.author.AuthorRepository;
import com.nyabuti.dennis.springboot.mongodb.repository.book.BookRepository;
import com.nyabuti.dennis.springboot.mongodb.repository.book_author.BookAuthorRepository;
import com.nyabuti.dennis.springboot.mongodb.repository.publisher.PublisherRepository;

@RunWith(SpringRunner.class)
@WebMvcTest(value = BookController.class, secure = false)
public class BookAuthorTest {
	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private BookRepository bookRepository;
	@MockBean
	private AuthorRepository authorRepository;
	@MockBean
	private BookAuthorRepository bookAuthorRepository;
	@MockBean
	private PublisherRepository publisherRepository;
	private List<Book> books = new ArrayList<>();
	private List<Author> authors = new ArrayList<>();
	
	private String exampleSaveBook = "  {\n" + 
//			"    \"id\": \"5b7b3acf611eab0cfcc9d352\",\n" + 
			"    \"publisherId\": \"5b7b28138ed9490c14511fcb\",\n" + 
			"    \"title\": \"Book0\",\n" + 
			"    \"summary\": null,\n" + 
			"    \"isbn\": null,\n" + 
			"    \"language\": \"English\",\n" + 
			"    \"pages\": 9\n" + 
			"  }";
	private String exampleUpdateBook = "  {\n" + 
			"    \"id\": \"1\",\n" + 
			"    \"publisherId\": \"5b7b28138ed9490c14511fcb\",\n" + 
			"    \"title\": \"Book1\",\n" + 
			"    \"summary\": null,\n" + 
			"    \"isbn\": null,\n" + 
			"    \"language\": \"English\",\n" + 
			"    \"pages\": 9\n" + 
			"  }";
	
	@Before
	public void setup() {
		Book book = new Book();
		book.setId("1");
		book.setTitle("Book0");
		book.setPublisherId(ObjectId.get());
		book.setLanguage("English");
		book.setPages(9);
		
		Book book1 = new Book();
		book1.setId("2");
		book1.setTitle("Book2");
		book1.setPublisherId(ObjectId.get());
		book1.setLanguage("English");
		book1.setPages(9);
		this.books.add(book);
		this.books.add(book1);
	}
	
	@Test
	public void getBookByIdTest() throws Exception {
		Mockito.when(bookRepository.findById(Mockito.anyString())).thenReturn(this.books.get(0));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/books/1/").accept(MediaType.APPLICATION_JSON);

		mockMvc.perform(requestBuilder).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$.id", equalTo("1"))).andExpect(jsonPath("$.title", equalTo("Book0")))
				.andExpect(jsonPath("$.language", equalTo("English")));
	}
	
	@Test
	public void getBooksTest() throws Exception {
		Mockito.when(bookRepository.getAllBooks()).thenReturn(this.books);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/books/").accept(MediaType.APPLICATION_JSON);

		mockMvc.perform(requestBuilder).andExpect(status().isOk())
				.andExpect(jsonPath("$", org.hamcrest.Matchers.hasSize(2)));
	}
	
	@Test
	public void createBookTest() throws Exception {
		Mockito.when(bookRepository.createBook(Mockito.<Book>any())).thenReturn(this.books.get(0));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/books/").accept(MediaType.APPLICATION_JSON)
				.content(exampleSaveBook).contentType(MediaType.APPLICATION_JSON);
		mockMvc.perform(requestBuilder).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8)).andExpect(jsonPath("id").value("1"))
				.andExpect(jsonPath("title").value("Book0")).andExpect(jsonPath("language").value("English"));
	}
	
	@Test
	public void updateBookTest() throws Exception {
		Mockito.when(bookRepository.patchBook(Mockito.<Book>any())).thenReturn(this.books.get(0));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.patch("/books/").accept(MediaType.APPLICATION_JSON)
				.content(exampleUpdateBook).contentType(MediaType.APPLICATION_JSON);
		mockMvc.perform(requestBuilder).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("title").value("Book0")).andExpect(jsonPath("language").value("English"));
	}
	
	@Test
	public void removeBookTest() throws Exception {
		Mockito.when(bookAuthorRepository.deleteByAuthorId(Mockito.anyString())).thenReturn(DeleteResult.acknowledged(1));
		Mockito.when(bookRepository.deleteBook(Mockito.anyString())).thenReturn(DeleteResult.acknowledged(1));

		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/books/1").accept(MediaType.APPLICATION_JSON);
		
		mockMvc.perform(requestBuilder)
			.andDo(print())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.deletedCount", equalTo(1)));
	}
}
