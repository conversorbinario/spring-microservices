package com.microservices.microservices.services;

import org.springframework.stereotype.Service;

@Service
public class ISBNValidator {

  public boolean isValidISBN(String isbn) {
    
    if (isbn.matches(".*\\D.*")) {
      return false;
    }
    return isbn.length() == 10 || isbn.length() == 13;
  }

}
