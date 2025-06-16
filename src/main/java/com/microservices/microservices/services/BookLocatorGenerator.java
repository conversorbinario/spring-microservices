package com.microservices.microservices.services;

import org.springframework.stereotype.Service;

import com.microservices.microservices.external_entities.Book;
import com.microservices.microservices.services_interfaces.BookSearcherInterface;

@Service
public class BookLocatorGenerator {
	
	
	private BookSearcherInterface bookSearcher;

	
	public BookLocatorGenerator() {
		super();
	}


	public BookSearcherInterface getBookSearcher() {
		return bookSearcher;
	}


	public void setBookSearcher(BookSearcherInterface bookSearcher) {
		this.bookSearcher = bookSearcher;
	}


	public String generateLocator(String isbn) {
		Book book = bookSearcher.getBook(isbn);

		StringBuilder stringBuilder = new StringBuilder(100);
		stringBuilder.append(isbn.substring(0, 4));
		stringBuilder.append(book.getTitle().split(" ").length);

		stringBuilder.append(book.getName().charAt(0));

		return stringBuilder.toString();

	}

}
