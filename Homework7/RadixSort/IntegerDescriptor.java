package com.svilenstefanov.blatt7a;

/**
 * Homework 7
 * @author Svilen Stefanov
 */
public class IntegerDescriptor implements KeyDescriptor<Integer> {
  @Override public int buckets () {
	  return 10;
  }

  @Override public int digits () {
	  return 10;
  }

  @Override public int key (Integer element, int digit) {
	  return (int)((element/Math.pow(10, digit))%10);		//if you count from 0 - else (digit - 1)
  }

}
