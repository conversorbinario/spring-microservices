package com.microservices.microservices.services_interfaces;

import com.microservices.microservices.external_entities.Book;

public interface BookSearcherInterface {
	

	public Book getBook(String isbn);
	
}
