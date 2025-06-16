package com.microservices.microservices.services;

import org.springframework.stereotype.Service;

@Service
public class ISBNValidator {

  public static final int SHORT_ISBN = 10;
  public static final int LONG_ISBN = 13;

public boolean isValidISBN(String isbn) {
    
    
    if (isbn.length() != 10 && isbn.length()!=ISBNValidator.LONG_ISBN) {
    	throw new IllegalArgumentException();
    }
    
    int stringLimitToCheck = (isbn.length() == SHORT_ISBN) ? isbn.length() -1 : isbn.length();
   
    String subtstr =  isbn.substring(0, stringLimitToCheck);
    
    if (subtstr.matches(".*\\D.*")) {
    	throw new NumberFormatException();
    }

    if((isbn.length() == SHORT_ISBN && isIsbn10ValidMod(isbn))){
      return true;
    }
    if((isbn.length() == LONG_ISBN && isIsbn13ValidMod(isbn))){
      return true;
    }
    return false;
  }

	private boolean isIsbn10ValidMod(String isbnString) {
		int i, s = 0, t = 0;

		for (i = 0; i < SHORT_ISBN; i++) {
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
