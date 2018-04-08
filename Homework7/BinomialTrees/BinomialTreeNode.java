package com.svilenstefanov.blatt7b;

import java.util.Arrays;

/**
 * Homework 7
 * @author Svilen Stefanov
 */
public class BinomialTreeNode {
	private int root;
	private BinomialTreeNode[] children; 
	
	public BinomialTreeNode(int root){
		this.root = root;
		children = new BinomialTreeNode[0];
	}
 
  /**
   * Ermittelt das minimale Element im Teilbaum.
   * 
   * @return das minimale Element
   */
  public int min() {
	  return root;
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
  
  /**
   * Diese Methode vereint zwei Baeume des gleichen Ranges.
   * 
   * @param a der erste Baum
   * @param b der zweite Baum
   * @return denjenigen der beiden Baeume, an den der andere angehaengt wurde
   */
  public static BinomialTreeNode merge(BinomialTreeNode a, BinomialTreeNode b) {
	  BinomialTreeNode[] newChildren;
	  if (a.min() > b.min()) {
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
}
