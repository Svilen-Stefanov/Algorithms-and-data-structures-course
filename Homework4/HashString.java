package com.svilenstefanov.blatt4;
/**
 * Homework 4
 * @author Svilen Stefanov
 */
import java.util.ArrayList;
import java.util.Random;
import java.util.stream.IntStream;

/**
 * Die Klasse {@link HashString} kann dazu verwendet werden,
 * Strings zu hashen.
 */
public class HashString {
	private int size;
	private ArrayList<Integer> aVector = new ArrayList<Integer>();

  /**
   * Dieser Konstruktor initialisiert ein {@link HashString}
   * Objekt fuer einen gegebenen Maximalwert (size - 1) der gehashten
   * Werte.
   * 
   * @param size die Groesse der Hashtabelle
   */
  public HashString (int size) {
	  this.size = size;
	  while(aVector.size() < size){			//initialize a vector of the corresponding to the hash table size
		  Random random = new Random();
		  aVector.add(random.nextInt(size));
	  }
  }

  /**
   * Diese Methode berechnet den Hashwert fuer einen String.
   * 
   * @param key der Schluessel, der gehasht werden sollen
   * @return der Hashwert des Schluessels
   */
  public int hash (String key) {
	  synchronized(aVector){
		  while(aVector.size() < key.length()){		//if the vector is smaller than the String key (the vector should be enlarged)
			  Random random = new Random();
			  aVector.add(random.nextInt(size));
		  }
		  int keyToInteger = 0;		//this is the key, which will be saved in the hash table
		  int i = 0;
		  for (char c: key.toCharArray()) {
				keyToInteger += c * aVector.get(i);		//the key is the sum of each character * the corresponding vector index
				i++;	
		  }
		  return keyToInteger%size;
	  }
  }
}
