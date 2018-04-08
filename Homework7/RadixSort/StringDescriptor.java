package com.svilenstefanov.blatt7a;

import java.util.HashSet;

/**
 * Homework 7
 * @author Svilen Stefanov
 */
public class StringDescriptor implements KeyDescriptor<String> {
	String[] elements;	
	
	  public StringDescriptor(String[] elements) {
		  this.elements = elements;
	  }

	  @Override public int buckets () {
		  HashSet<String> sizeBuckets = new HashSet<String>();
		  for (int i = 0; i < elements.length; i++) {
			for (char c: elements[i].toCharArray()) 
				sizeBuckets.add(c + "");
		  }
	    return 63 /*sizeBuckets.size()*/;
	  }

	  @Override public int digits () {
		  int digits = 0;
		  for (int i = 0; i < elements.length; i++) {
			  digits = elements[i].length() > digits ? elements[i].length() : digits;
		}
		  return digits;
	  }
	
	  @Override public int key (String element, int digit) {
	            int a = element.charAt(element.length() - 1 - digit);
	            if (a >= 'a' && a <= 'z') {
	      		  return 12 + 2*(a - 'a');
	            } else if(a >= 'A' && a <= 'Z'){
	              return 11 + 2*(a - 'A');
	            }
	            else if(a >= '0' && a <= '9') return a - 48;
	            else throw new IllegalArgumentException();
	  }

}
