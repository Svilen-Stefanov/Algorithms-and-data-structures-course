package com.svilenstefanov.blatt7b;

import java.util.ArrayList;

/**
 * Homework 7
 * @author Svilen Stefanov
 */
public class BinomialHeap {
	private ArrayList<BinomialTreeNode> elements;
	private BinomialTreeNode minElement;
  
  /**
   * Dieser Konstruktor baut einen leeren Haufen.
   */
  public BinomialHeap() {
	  elements = new ArrayList<BinomialTreeNode>();
	  minElement = null;
  }
  
  /**
   * Diese Methode fuegt einen Wert in den Haufen ein.
   * 
   * @param value der einzufuegende Wert
   */
  public void insert(int value) {
	  if(elements.size()==0){
		  minElement = new BinomialTreeNode(value);
		  elements.add(0, minElement);
	  } else {
		BinomialTreeNode insertedNode;
		insertedNode = new BinomialTreeNode(value);
		if(elements.get(elements.size() - 1).rank() != 0){
			elements.add(insertedNode);
		} else {
			insertedNode = insertedNode.merge(insertedNode, elements.get(elements.size() - 1));
			elements.remove(elements.size() - 1);
			while(elements.size() > 0 && insertedNode.rank() == elements.get(elements.size() - 1).rank()){
					insertedNode = insertedNode.merge(insertedNode, elements.get(elements.size() - 1));
					elements.remove(elements.size() - 1);
			}
			elements.add(insertedNode);
		}
		for (int i = 0; i < elements.size(); i++){
			if(elements.get(i).min() < minElement.min())
				minElement = elements.get(i);
		}	
	  }
  }
  
  /**
   * Diese Methode ermittelt das minimale Element im binomialen
   * Haufen.
   * 
   * @return das minimale Element
   */
  public int min() {
	  return minElement.min();
  }
  
  /**
   * Diese Methode entfernt das minimale Element aus dem binomialen
   * Haufen und gibt es zurueck.
   * 
   * @return das minimale Element
   */
  public int deleteMin() {
	  int min = minElement.min();
	  BinomialTreeNode[] minChildren = minElement.deleteMin();
	  for (int i = 0; i < elements.size(); i++) {
		if(minElement == elements.get(i))
			elements.remove(i);
	  }
	  for (int i = 0; i < minChildren.length; i++) {
		  for (int j = elements.size() - 1; j > 0 ; j--) {
			if(minChildren[i].rank() == elements.get(j).rank()){
				while(j > 0 && minChildren[i].rank() == elements.get(j).rank()){
				minChildren[i] = minChildren[i].merge(minChildren[i], elements.get(j));
				elements.remove(j);
				j = elements.size() - 1;
				}
				break;
			}
		  }
		  elements.add(minChildren[i]);
	  }
	  	if(elements.size() > 0)
	  		minElement = elements.get(0);
	  	else minElement = null;
		for (int i = 0; i < elements.size(); i++){
			if(elements.get(i).min() < minElement.min())
				minElement = elements.get(i);
		}	
	  return min;
  }
}
