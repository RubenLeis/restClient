package com.mkyong.httpclient;

import static org.junit.Assert.fail;

import java.io.IOException;

import org.junit.Test;

import com.mkyong.Book;

import es.caixagalicia.rest.client.JsonMapper;

public class JsonMapperTest {
	@Test
	public void readValueTest()  {
		String json = "{" + "\"id\": 3,\r\n" + "\"name\": \"Refactoring: Improving the Design of Existing Code\",\r\n"
				+ "\"author\": \"Junit\",\r\n" + "\"price\": 47.99\r\n" + "}";

		JsonMapper<Book,Book> jsonMapper = new JsonMapper<Book,Book>(Book.class);
		try {
			Book book = jsonMapper.readValue(json);
			System.out.println(book);
		} catch (IOException  e) {
			fail();
		}
		

	}
	
	@Test
	public void writeValueAsString()  {
		Book book = new Book();
		book.setAuthor("Autor");
		JsonMapper<Book,Book> jsonMapper = new JsonMapper<Book,Book>(Book.class);
		try {
			String writeValueAsString = jsonMapper.writeValueAsString(book);
			System.out.println(writeValueAsString);
		} catch (IOException  e) {
			fail();
		}
	}
}
