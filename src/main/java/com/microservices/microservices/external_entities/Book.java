package com.microservices.microservices.external_entities;

public class Book {

	

	private String name;

	public String title;

	private String isbn;
	
	public Book(String name, String title, String isbn) {
		super();
		this.name = name;
		this.title = title;
		this.isbn = isbn;
	}
	

	public Book() {
		super();
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTiel(String isbn) {
		this.title = isbn;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

}