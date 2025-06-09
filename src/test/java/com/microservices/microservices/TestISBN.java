package com.microservices.microservices;
import com.microservices.microservices.services.ISBNValidator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestISBN {

    @Autowired
    private ISBNValidator isbnValidator;

    @Test
    public void testIsValidClassic10DigitISBN() {
        assertTrue(isbnValidator.isValidISBN("1544512279"));
        assertTrue(isbnValidator.isValidISBN("0306406152"));
        
        assertTrue(isbnValidator.isValidISBN("9788420412146"));
        assertTrue(isbnValidator.isValidISBN("9781544512273"));

    }
    
    @Test
	  public void testIsValidISBNWithJustLastCharLetterX() {
		  assertTrue(isbnValidator.isValidISBN("012000030X"));
	 
	  }
    
    
    @Test
    public void testIsValidClassic13DigitISBN() {
      
        assertTrue(isbnValidator.isValidISBN("9788491294283"));
        assertTrue(isbnValidator.isValidISBN("9788420412146"));
        assertTrue(isbnValidator.isValidISBN("9781544512273"));

    }
    	  
	 
	 

    @Test
    public void testIsISBNOfValidLengthInvalidISBN() {
        assertFalse(isbnValidator.isValidISBN("8498653437"));
        assertFalse(isbnValidator.isValidISBN("9781544512271"));
        assertFalse(isbnValidator.isValidISBN("9781544512274"));
    }
    
    @Test
    public void testIsCombinationOfLettersAndNumbersISBNNotAllowed() {
		assertThrows(NumberFormatException.class, () -> {
			isbnValidator.isValidISBN("12345678aa");
		});
		assertThrows(NumberFormatException.class, () -> {
			isbnValidator.isValidISBN("hola amigo");
		});

    }
    
    @Test
    public void testIsNonCompliantISBNLengthNotAllowed() {
		assertThrows(IllegalArgumentException.class, () -> {
			isbnValidator.isValidISBN("123456789a1");
		});

    }
    
   // 012000030X
    
    

}
