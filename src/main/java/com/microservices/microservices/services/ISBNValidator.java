package com.microservices.microservices.services;

import org.springframework.stereotype.Service;

@Service
public class ISBNValidator {

  public boolean isValidISBN(String isbn) {
    
    
    if (isbn.length() != 10 && isbn.length()!=13) {
    	throw new IllegalArgumentException();
    }
    
    int stringLimitToCheck = isbn.length();
   
    String subtstr =  isbn.substring(0, stringLimitToCheck-1);
    
    if (subtstr.matches(".*\\D.*")) {
    	throw new NumberFormatException();
    }

    if((isbn.length() == 10 && isIsbn10ValidMod(isbn))){
      return true;
    }
    if((isbn.length() == 13 && isIsbn13ValidMod(isbn))){
      return true;
    }
    return false;
  }

	private boolean isIsbn10ValidMod(String isbnString) {
		int i, s = 0, t = 0;

		for (i = 0; i < 10; i++) {
			t += (i == 9 && isbnString.charAt(i) == 'X') ? 10 : Character.getNumericValue(isbnString.charAt(i));
			s += t;
		}
		return s % 11 == 0;
	}

  private boolean isIsbn13ValidMod(String isbnString){
    int i, sumatorioWeightedDigits = 0;

    for (i = 1; i < 14; i++) {
      int digitoI = isbnString.charAt(i-1); //[i-1];
      if (i % 2 == 0) {
        sumatorioWeightedDigits+=(digitoI * 3);
        continue;
      }
      sumatorioWeightedDigits+=digitoI;

    }

    return (sumatorioWeightedDigits % 10 == 0); 
  }

}
