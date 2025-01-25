package com.microservices.microservices;
import com.microservices.microservices.services.ISBNValidator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestISBN {

    @Autowired
    private ISBNValidator isbnValidator;

    @Test
    public void testIsValidISBN() {
        assertTrue(isbnValidator.isValidISBN("8498653436"));
    }

    @Test
    public void testIsInvalidISBN() {
        assertFalse(isbnValidator.isValidISBN("849865343a"));
        assertFalse(isbnValidator.isValidISBN("8498653437"));

    }

}
