package com.microservices.microservices;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.LinkedList;
import java.util.Queue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
//import org.testcontainers.shaded.com.google.common.base.Verify;

import com.microservices.microservices.external_entities.Book;
import com.microservices.microservices.services.BookLocatorGenerator;
import com.microservices.microservices.services_interfaces.BookSearcherInterface;

@SpringBootTest
class TestBookLocator {

	@Autowired
	BookLocatorGenerator bookLocatorGenerator;

	@Test
	void isCalculatedCodeFromBookValid() {
		
		String isbn = "1544512279";

		BookSearcherInterface concretBookSearcher = mock(BookSearcherInterface.class);
		when(concretBookSearcher.getBook(isbn)).thenReturn(new Book("Julio Llamazares", "La lluvia amarilla", isbn));
		

		BookLocatorGenerator bl = new BookLocatorGenerator();
		bl.setBookSearcher(concretBookSearcher);

		String isbnLocator = bl.generateLocator(isbn);
		assertEquals("15443J", isbnLocator);

		
		
		isbn = "2344512279";
		concretBookSearcher = mock(BookSearcherInterface.class);
		when(concretBookSearcher.getBook(isbn)).thenReturn(new Book("Alvaro Cunqueiro", "As crónicas do Sochantre", isbn));



		bl = new BookLocatorGenerator();
		bl.setBookSearcher(concretBookSearcher);

		isbnLocator = bl.generateLocator(isbn);
		assertEquals("23444A", isbnLocator);

	}
	
	@Test
	void isLocalDataDbDataUsedIfBookIsPresentInIt() {
		BookSearcherInterface mockedDbSearcher = mock(BookSearcherInterface.class);
		BookSearcherInterface mockedWebServiceSearcher = mock(BookSearcherInterface.class);
		
		Queue<BookSearcherInterface> bSearchers = new LinkedList<>();
		
		bSearchers.add(mockedDbSearcher);
		bSearchers.add(mockedWebServiceSearcher);
		
		
		String isbnOfDbBook = "2344512279";
		when(mockedDbSearcher.getBook(isbnOfDbBook)).thenReturn(new Book("Alvaro Cunqueiro", "As crónicas do Sochantre", "2344512279"));
		when(mockedWebServiceSearcher.getBook(isbnOfDbBook)).thenReturn(new Book("Alvaro Cunqueiro", "As crónicas do Sochantre", "2344512279"));

		BookLocatorGenerator bl = new BookLocatorGenerator();

		//Cola!
		while (!bSearchers.isEmpty()) {
		    BookSearcherInterface bSearcher = bSearchers.poll(); 
			bl.setBookSearcher(bSearcher);
			
		    Book book = bSearcher.getBook(isbnOfDbBook);
		    if (book != null) {
		        break;
		    }
		}
		
			
		verify(mockedDbSearcher, times(1)).getBook(isbnOfDbBook);
		verify(mockedWebServiceSearcher, times(0)).getBook(isbnOfDbBook);
		
		
		
	}
	
	@Test
	void isWebServiceUsedIfBookPresentInIt() {
		BookSearcherInterface mockedDbSearcher = mock(BookSearcherInterface.class);
		BookSearcherInterface mockedWebServiceSearcher = mock(BookSearcherInterface.class);
		
		Queue<BookSearcherInterface> bSearchers = new LinkedList<>();
		
		bSearchers.add(mockedWebServiceSearcher);
		bSearchers.add(mockedDbSearcher);

		
		String isbnOfDbBook = "2344512279";
		when(mockedDbSearcher.getBook(isbnOfDbBook)).thenReturn(new Book("Alvaro Cunqueiro", "As crónicas do Sochantre", "2344512279"));
		when(mockedWebServiceSearcher.getBook(isbnOfDbBook)).thenReturn(new Book("Alvaro Cunqueiro", "As crónicas do Sochantre", "2344512279"));

		BookLocatorGenerator bl = new BookLocatorGenerator();

		//Cola!
		while (!bSearchers.isEmpty()) {
		    BookSearcherInterface bSearcher = bSearchers.poll(); 
			bl.setBookSearcher(bSearcher);
			
		    Book book = bSearcher.getBook(isbnOfDbBook);
		    if (book != null) {
		        break;
		    }
		}
		
			
		verify(mockedDbSearcher, times(0)).getBook(isbnOfDbBook);
		verify(mockedWebServiceSearcher, times(1)).getBook(isbnOfDbBook);
		
	}

}
