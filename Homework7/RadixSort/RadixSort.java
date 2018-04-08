package com.svilenstefanov.blatt7a;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * Homework 7
 * @author Svilen Stefanov
 */
public class RadixSort<K> {
	KeyDescriptor<K> keyDescriptor;
	ArrayList[] buckets;

  public RadixSort(KeyDescriptor<K> keyDescriptor) {
	  	this.keyDescriptor = keyDescriptor;
	  	buckets = new ArrayList[keyDescriptor.buckets()];
		for (int i = 0; i < buckets.length; i++) 
			buckets[i] = new ArrayList<K>();
	}

private void kSort(K[] elements, int digit) {
	String elementString = new String();
	if(keyDescriptor instanceof IntegerDescriptor){
		for (int i = 0; i < elements.length; i++) {	
			if((int)elements[i] < 10*digit)
				buckets[0].add((int)elements[i]);
			else buckets[keyDescriptor.key(elements[i],digit)].add((int)elements[i]);
		}
	} else {
		for (int i = 0; i < elements.length; i++) {
			elementString = (String)elements[i];
			while (elementString.length() < keyDescriptor.digits())
				elementString = elementString + "z";	//works as least significant character
			buckets[keyDescriptor.key((K) elementString,digit)].add(elements[i]);
		}
	}
	
	int insertedElem = 0;
	for (int i = 0; i < buckets.length; i++) {
		while(buckets[i].size()!=0){
			elements[insertedElem++] = (K)buckets[i].get(0);
			buckets[i].remove(0);
		}
	}
  }
  
  public void sort(K[] elements) {
	  for (int i = 0; i < keyDescriptor.digits(); i++)
		kSort(elements, i);
  }

  /**
   * Diese Methode sortiert ein Feld mittels RadixSort.
   * 
   * @param keyDescriptor ein Objekt zur Ermittelung von Informationen ueber die zu sortierenden Elemente
   * @param elements die zu sortierenden Elemente
   */
  public static <K> void sort(KeyDescriptor<K> keyDescriptor, K[] elements) {
    RadixSort<K> radixSort = new RadixSort<K>(keyDescriptor);
    radixSort.sort(elements);
  }
}
