package com.microservices.microservices;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.microservices.microservices.external_entities.Book;
import com.microservices.microservices.services.BookLocatorGenerator;
import com.microservices.microservices.services_interfaces.BookSearcherInterface;

@SpringBootTest
class TestBookLocator {

	@Autowired
	BookLocatorGenerator bookLocatorGenerator;

	@Test
	void isValidCode() {
		String isbn = "1544512279";

		BookSearcherInterface concretBookSearcher = new BookSearcherInterface() {

			@Override
			public Book getBook(String isbn) {
				return new Book("Julio Llamazares", "La lluvia amarilla", isbn);
			}
		};

		BookLocatorGenerator bl = new BookLocatorGenerator();
		bl.setBookSearcher(concretBookSearcher);

		String isbnLocator = bl.generateLocator(isbn);
		assertEquals("15443J", isbnLocator);


		isbn = "2344512279";

		concretBookSearcher = new BookSearcherInterface() {

			@Override
			public Book getBook(String isbn) {
				return new Book("Alvaro Cunqueiro", "As crónicas do Sochantre", isbn);
			}
		};

		bl = new BookLocatorGenerator();
		bl.setBookSearcher(concretBookSearcher);

		isbnLocator = bl.generateLocator(isbn);
		assertEquals("23444A", isbnLocator);

	}

}
