package com.microservices.microservices.services;

import org.springframework.stereotype.Service;

@Service
public class ISBNValidator {

  public boolean isValidISBN(String isbn) {
    
    if (isbn.matches(".*\\D.*")) {
    	throw new NumberFormatException();
    }
    if (isbn.length() != 10 && isbn.length()!=13) {
    	throw new IllegalArgumentException();
    }
    
    int[] numericIsbn  = new int[isbn.length()];

    for(int i = 0; i< isbn.length(); i++){
      numericIsbn[i] =  Character.getNumericValue(isbn.charAt(i));
    }

    if((isbn.length() == 10 && isIsbn10ValidMod(numericIsbn))){
      return true;
    }
    if((isbn.length() == 13 && isIsbn13ValidMod(numericIsbn))){
      return true;
    }
    return false;
  }

	private boolean isIsbn10ValidMod(int[] isbnArray) {
		int i, s = 0, t = 0;

		for (i = 0; i < 10; i++) {
			t += isbnArray[i];
			s += t;
		}
		return s % 11 == 0;
	}

  private boolean isIsbn13ValidMod(int[] isbnArray){
    int i, sumatorioWeightedDigits = 0;

    for (i = 1; i < 14; i++) {
      int digitoI = isbnArray[i-1];
      if (i % 2 == 0) {
        sumatorioWeightedDigits+=(digitoI * 3);
        continue;
      }
      sumatorioWeightedDigits+=digitoI;

    }

    return (sumatorioWeightedDigits % 10 == 0); 
  }

}
