package com.svilenstefanov.blatt10.a6;

import java.util.Arrays;

import com.svilenstefanov.blatt7b.BinomiliaTest;

/**
 * Homework 10
 * @author Svilen Stefanov
 */
public class BinomialTreeNode<T extends Comparable<T>>{
	private T root;
	private BinomialTreeNode<T>[] children; 
	
	public BinomialTreeNode(T root){
		this.root = root;
		children = new BinomialTreeNode[0];
	}
 
  /**
   * Ermittelt das minimale Element im Teilbaum.
   * 
   * @return das minimale Element
   */
  public T min() {
	  return (T)root;
  }
  
  /**
   * Gibt den Rang des Teilbaumes zurueck.
   * 
   * @return der Rang des Teilbaumes
   */
  public int rank() {
	  return children.length;
  }
  
  /**
   * Gibt eine Menge von Teilbaeumen zurueck, in die der
   * aktuelle Baum zerfaellt, wenn man den Knoten des minimalen
   * Elements entfernt.
   * 
   * @return die Menge von Teilbaeumen
   */
  public BinomialTreeNode[] deleteMin() {
	return children;
  }
  
  public void siftUp(){
	  //TODO
  }
  
  /**
   * Diese Methode vereint zwei Baeume des gleichen Ranges.
   * 
   * @param a der erste Baum
   * @param b der zweite Baum
   * @return denjenigen der beiden Baeume, an den der andere angehaengt wurde
   */
  public BinomialTreeNode merge(BinomialTreeNode<T> a,BinomialTreeNode<T> b) {
	  BinomialTreeNode[] newChildren;
	  if (((Comparable<T>)a.min()).compareTo(b.min()) < 0) {
		  newChildren = new BinomialTreeNode[b.children.length + 1];
		  for (int i = 0; i < b.children.length; i++) 
			newChildren[i] = b.children[i];
		  newChildren[b.children.length] = a;
		  b.children = newChildren;
		  return b;
	  }
	  else {
		  newChildren = new BinomialTreeNode[a.children.length + 1];
		  for (int i = 0; i < a.children.length; i++) 
			newChildren[i] = a.children[i];
		  newChildren[a.children.length] = b;
		  a.children = newChildren;
		  return a;
	  }
  }
  
  public void decreaseKey(T key){
	this.root = key;
	siftUp();
  }
  
}
