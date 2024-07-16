package com.example.accessingdata;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.URI;
import java.net.URISyntaxException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.accessingdata.models.Address;
import com.example.accessingdata.models.Author;
import com.example.accessingdata.models.Book;
import com.example.accessingdata.models.Library;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
class AccessingdataApplicationTests {

	@Autowired
	private TestRestTemplate template;

	private static String BOOK_ENDPOINT = "http://localhost:8080/books";
	private static String AUTHOR_ENDPOINT = "http://localhost:8080/authors";
	private static String ADDRESS_ENDPOINT = "http://localhost:8080/addresses";
	private static String LIBRARY_ENDPOINT = "http://localhost:8080/libraries";

	private static String LIBRARY_NAME = "My Library";
	private static String AUTHOR_NAME = "George Orwell";

	@SuppressWarnings("null")
	@Test
	public void whenSaveOneToOneRelationship_thenCorrect() {
		Library library = new Library(LIBRARY_NAME);
		template.postForEntity(LIBRARY_ENDPOINT, library, Library.class);

		Address address = new Address("Main street, nr 1");
		template.postForEntity(ADDRESS_ENDPOINT, address, Address.class);

		// String libraryAddressUrl = "libraryAddress";
		String libraryAddressUrl = "address";

		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.add("Content-type", "text/uri-list");
		HttpEntity<String> httpEntity = new HttpEntity<>(ADDRESS_ENDPOINT + "/1", requestHeaders);
		template.exchange(LIBRARY_ENDPOINT + "/1/" + libraryAddressUrl,
				HttpMethod.PUT, httpEntity, String.class);

		URI uir;
		try {
			uir = new URI(ADDRESS_ENDPOINT + "/1/library");
			ResponseEntity<Library> libraryGetResponse = template.getForEntity(uir, Library.class);
			assertEquals(LIBRARY_NAME,
					libraryGetResponse.getBody().getName(), "library is incorrect");
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("null")
	@Test
	public void whenSaveOneToManyRelationship_thenCorrect() {
		Book book1 = new Book("Dune");
		template.postForEntity(BOOK_ENDPOINT, book1, Book.class);

		Book book2 = new Book("1984");
		template.postForEntity(BOOK_ENDPOINT, book2, Book.class);

		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.add("Content-Type", "text/uri-list");
		HttpEntity<String> bookHttpEntity = new HttpEntity<>(LIBRARY_ENDPOINT + "/1", requestHeaders);

		template.exchange(BOOK_ENDPOINT + "/1/library",
				HttpMethod.PUT, bookHttpEntity, String.class);
		template.exchange(BOOK_ENDPOINT + "/2/library",
				HttpMethod.PUT, bookHttpEntity, String.class);

		URI uri;
		try {
			uri = new URI(BOOK_ENDPOINT + "/1/library");
			ResponseEntity<Library> libraryGetResponse = template.getForEntity(uri, Library.class);
			try {
				assertEquals(LIBRARY_NAME,
						libraryGetResponse.getBody().getName(), "library is incorrect");
			} catch (java.lang.NullPointerException e) {
				e.printStackTrace();
			}
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("null")
	@Test
	public void createAuthor() {
		Author author1 = new Author(AUTHOR_NAME);
		template.postForEntity(AUTHOR_ENDPOINT, author1, Author.class);
		try {
			ResponseEntity<Author> authorGetResponse = template.getForEntity(AUTHOR_ENDPOINT + "/1", Author.class);
		} catch (java.lang.NullPointerException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void whenSaveManyToManyRelationship_thenCorrect() {
		Book book1 = new Book("Animal Farm");
		template.postForEntity(BOOK_ENDPOINT, book1, Book.class);

		Book book2 = new Book("1985");
		template.postForEntity(BOOK_ENDPOINT, book2, Book.class);

		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.add("Content-type", "text/uri-list");
		HttpEntity<String> httpEntity = new HttpEntity<>(
				BOOK_ENDPOINT + "/3\n" + BOOK_ENDPOINT + "/4", requestHeaders);
		template.exchange(AUTHOR_ENDPOINT + "/1/books",
				HttpMethod.PUT, httpEntity, String.class);

		try {
			String jsonResponse = template
					.getForObject(BOOK_ENDPOINT + "/1/authors", String.class);
			JSONObject jsonObj = new JSONObject(jsonResponse).getJSONObject("_embedded");
			JSONArray jsonArray = jsonObj.getJSONArray("authors");
			assertEquals(AUTHOR_NAME,
					jsonArray.getJSONObject(0).getString("name"), "author is incorrect");
		} catch (JSONException | java.lang.NullPointerException e) {
			e.printStackTrace();
		}
	}

}
