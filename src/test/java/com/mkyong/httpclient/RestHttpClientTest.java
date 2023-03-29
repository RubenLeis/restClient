package com.mkyong.httpclient;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.io.IOException;

import org.junit.Ignore;
import org.junit.Test;

import es.caixagalicia.rest.client.RestClient;
import es.caixagalicia.rest.client.to.RestClientException;
import es.caixagalicia.rest.client.to.RestResponse;

@Ignore
public class RestHttpClientTest {

	@Test
	public void findTest() {
		RestClient restClient = new RestClient();
		try {
			RestResponse restResponse = restClient.doGet("http://localhost:8081/books");

			assertNotNull(restResponse);
			assertNotNull(restResponse.getStatusLine());
			assertEquals(200, restResponse.getStatusLine().getStatusCode());
			System.out.println(restResponse.getEntityAsString());

		} catch (RestClientException e) {
			fail();
		}

	}

	@Test
	public void patchTest() throws RestClientException {
		RestClient restClient = new RestClient();
		String json = "{" + "\"id\": 3,\r\n" + "\"name\": \"Refactoring: Improving the Design of Existing Code\",\r\n"
				+ "\"author\": \"Junit\",\r\n" + "\"price\": 47.99\r\n" + "}";

		try {
			RestResponse restResponse = restClient.doPatch("http://localhost:8081/books/3", json);
			assertNotNull(restResponse);
			assertNotNull(restResponse.getStatusLine());
			assertEquals(200, restResponse.getStatusLine().getStatusCode());
			System.out.println(restResponse.getEntityAsString());
		} catch (RestClientException e) {
			fail();
		}

	}

	@Test
	public void saveJsonTest() throws IOException, RestClientException {
		RestClient restClient = new RestClient();
		String json = "{" + "\"id\": 4,\r\n" + "\"name\": \"New book\",\r\n" + "\"author\": \"Junit\",\r\n"
				+ "\"price\": 12.34\r\n" + "}";

		try {
			RestResponse restResponse = restClient.doPost("http://localhost:8081/books/", json);
			assertNotNull(restResponse);
			assertNotNull(restResponse.getStatusLine());
			assertEquals(201, restResponse.getStatusLine().getStatusCode());
			System.out.println(restResponse.getEntityAsString());
		} catch (RestClientException e) {
			fail();
		}
	}
	
	
	//TODO: podriamos parametrizarlo
	//@Test
//	public void saveBookObjectTest() throws IOException, RestClientException {
//		RestClient restClient = new RestClient();
//		try {
//			Book book = new Book();
//			book.setId(5);
//			book.setName("Book object");
//			book.setAuthor("Junit");
//			book.setPrice(new BigDecimal("12.34"));
//			RestResponse restResponse = restClient.doPost("http://localhost:8080/books/", book);
//			assertNotNull(restResponse);
//			assertNotNull(restResponse.getStatusLine());
//			assertEquals(201, restResponse.getStatusLine().getStatusCode());
//			System.out.println(restResponse.getEntityAsString());
//		} catch (RestClientException e) {
//			fail();
//		}
//	}

}
