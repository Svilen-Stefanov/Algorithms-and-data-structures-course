package com.svilenstefanov.blatt9;
/**
 * Homework 9
 * @author Svilen Stefanov
 */
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Diese Klasse implementiert einen (a,b)-Baum.
 */
public class ABTree {
  /**
   * Diese Variable speichert die untere Schranke des Knotengrades.
   */
  private int a;

  /**
   * Diese Variable speichert die obere Schranke des Knotengrades.
   */
  private int b;

  /**
   * Diese Klasse repraesentiert einen Knoten des Baumes. Es kann sich
   * dabei um einen inneren Knoten oder ein Blatt handeln.
   */
  public abstract class ABTreeNode {
    /**
     * Diese Methode wandelt den Baum in das Graphviz-Format um.
     * 
     * @return der Baum im Graphiz-Format
     */
    public abstract int dot (StringBuilder sb, int from);
    public boolean find(int key){return true;};
    public boolean makeItABTree(int key, boolean isHereTheProblem){return true;};
    public boolean validAB (){return true;};
    public void insert(int key) {};
  }

  /**
   * Diese Klasse repraesentiert einen inneren Knoten des Baumes.
   */
  private class ABTreeInnerNode extends ABTreeNode {
	 private ArrayList<ABTreeNode> children = new ArrayList<>();
	 private ArrayList<Integer> keys = new ArrayList<>();

	 public ABTreeInnerNode (int key) {
		      this(key, new ABTreeLeaf(), new ABTreeLeaf());
	 }
	 
	 public ABTreeInnerNode (int key, ABTreeNode leftChild, ABTreeNode rightChild) {
	    keys = new ArrayList<Integer>();
        children = new ArrayList<ABTree.ABTreeNode>();
        keys.add(key);
	    children.add(leftChild);
        children.add(rightChild);
     }
	 
	 public ABTreeInnerNode (ArrayList<Integer> keys, ArrayList<ABTreeNode> children) {
	      this.keys = keys;
	      this.children = children;
	 }

	/***
	  * This is a helper method to find recursively whether the key exists in the (a, b) tree
	  * @param key searched key
	  * @return true if found <br> false if not found
	  */
	 public boolean find (int key) {
		 if(this.keys == null)
			 return false;

		 for (int i = 0; i < this.keys.size(); i++) {
			  if(key == this.keys.get(i))
				  return true;
			  else if(key < this.keys.get(i)){
				  if(this.children.get(i) != null)
					  return this.children.get(i).find(key);
				  else return false;
			  }
			  else {
				  if(key < this.keys.get(i+1)){
					  if(this.children.get(i+1) != null)
						return this.children.get(i+1).find(key);
					  else return false;
				  } else if(i == this.keys.size() - 1){
					 if(this.children.get(i+1) != null)
						 return this.children.get(i+1).find(key);
					 else return false;
				  } else continue;
			  }
		  }
		  return false;
	 }
	 
	 /***
	  * This is a helper method to check recursively whether the (a, b) tree meets all conditions of (a, b) trees
	  * @return true if valid <br> false if not valid
	  */
	 public boolean validAB () {
		 if(this.children != null){
			 if(this.children.size() > b || this.children.size() < a)
				 return false;
		 }
		
		 for (int i = 1; i < this.keys.size(); i++) {
			if(this.keys.get(i) < this.keys.get(i-1))
				return false;
		 }
		  
		 for (int i = 0; i < this.children.size(); i++){
			 if(!this.children.get(i).validAB())
				return false;
		 }
		  
		  return true;
	 }
	 
	 public void insert(int key) {
		 ABTreeInnerNode newNode = new ABTreeInnerNode(key);
		 if(this.keys != null){
			  for (int i = 0; i < this.keys.size(); i++) {
				  if(key < this.keys.get(i))
					  this.children.get(i).insert(key);
				  else {
					  if(i == this.keys.size() - 1)
							this.children.get(i+1).insert(key);
					  else if(key < root.get().keys.get(i+1))
						 this.children.get(i).insert(key);
					  else continue;
				  }
			  }
		 } else return;
	 }
	  
	  /**
	   * This is a helper method to remove recursively a key from the (a, b) tree and notify whether it was successful 
	   * @param key the key to be removed
	   * @param isHereTheProblem show whether the problem is in this node (true), or whether the method should check in the child nodes
	   * @return true if key was found and successfully deleted <br> false if no key was found
	   */
	  public boolean makeItABTree(int key, boolean isHereTheProblem){
		  //must make the AB tree a correct AB tree
		  return true;
	  }
	  
    @Override public int dot (StringBuilder sb, int from) {
      int mine = from++;
      sb.append("\tstruct").append(mine).append(" [label=\"");
      for (int i = 0; i < 2*keys.size() + 1; i++) {
        if (i > 0)
          sb.append("|");
        sb.append("<f").append(i).append("> ");
        if(i % 2 == 1)
          sb.append(keys.get(i/2));
      }
      sb.append("\"];\n");
      for (int i = 0; i < children.size(); i++) {
        int field = 2*i;
        sb.append("\tstruct").append(mine).append(":<f").append(field)
            .append(">").append(" -> ").append("struct").append(from).append(";\n");
        from = children.get(i).dot(sb, from);
      }
      return from;
    }
  }

  /**
   * Diese Klasse repraesentiert ein Blatt des Baumes.
   */
  private class ABTreeLeaf extends ABTreeNode {
    @Override public int dot (StringBuilder sb, int from) {
      sb.append("\tstruct").append(from).append(" [label=leaf, shape=ellipse];\n");
      return from + 1;
    }
  }

  public ABTree (int a, int b) {
    if(a < 2)
      throw new RuntimeException("Invalid a");
    else if(b < 2*a - 1)
      throw new RuntimeException("Invalid b");
    this.a = a;
    this.b = b;
  }

  /**
   * Diese Objektvariable speichert die Wurzel des Baumes.
   */
  private Optional<ABTreeInnerNode> root = Optional.empty();

  /**
   * Diese Methode ist zum Debuggen gedacht und prueft, ob es sich
   * um einen validen (a,b)-Baum handelt.
   * 
   * @return 'true' falls der Baum ein valider (a,b)-Baum ist, 'false' sonst
  */
  public boolean validAB () {
	  //check whether the conditions for a and b are met
	  if(root.isPresent() && root.get().children != null){
		  if(root.get().children.size() > b || root.get().children.size() < a)
			  return false;
	  }
	  //check whether the keys are sorted right
	  for (int i = 1; i < root.get().keys.size(); i++) {
		if(root.get().keys.get(i) < root.get().keys.get(i-1))
			return false;
	  }
	  
	  //check whether the children subtrees are also valid
	  for (int i = 0; i < root.get().children.size(); i++){
		  if(!root.get().children.get(i).validAB())
			  return false;
	  }
	  
	  return true;
  }

  /**
   * Diese Methode ermittelt die Hoehe des Baumes.
   * 
   * @return die ermittelte Hoehe
   */
  public int height() {			
	  int height = 0;		//all leafs have the same height
	  ABTreeInnerNode search = root.get();
	  while(search.children.get(0) != null){
		  search = (ABTreeInnerNode)search.children.get(0);
		  height++;
	  }
	  return height;
  }

  /**
   * Diese Methode sucht einen Schluessel im (a,b)-Baum.
   * 
   * @param key der Schluessel, der gesucht werden soll
   * @return 'true', falls der Schluessel gefunden wurde, 'false' sonst
   */
  public boolean find (int key) {
	  if(root.isPresent() && root.get().keys != null){
		  for (int i = 0; i < root.get().keys.size(); i++) {
			  if(key == root.get().keys.get(i))
				  return true;
			  else if(key < root.get().keys.get(i))
				  return root.get().children.get(i).find(key);
			  else {
				  if(i == root.get().keys.size() - 1)
						 return root.get().children.get(i+1).find(key);
				  else if(key < root.get().keys.get(i+1))
					 return root.get().children.get(i).find(key);
				  else continue;
			  }
		  }
	  }
	  return false;
  }

  /**
   * Diese Methode fuegt einen neuen Schluessel in den (a,b)-Baum ein.
   * 
   * @param key der einzufuegende Schluessel
   */
  public void insert (int key) {
	  if(!root.isPresent())
		root = Optional.of(new ABTreeInnerNode(key));
	  if(root.isPresent() && root.get().keys != null){
		  root.get().insert(key);
	  }
  }

  /**
   * Diese Methode loescht einen Schluessel aus dem (a,b)-Baum.
   * 
   * @param key der zu loeschende Schluessel
   * @return 'true' falls der Schluessel gefunden und geloescht wurde, 'false' sonst
   */
  public boolean remove (int key) {
	  if(root.isPresent() && root.get().keys != null){
		  for (int i = 0; i < root.get().keys.size(); i++) {
			  if(key == root.get().keys.get(i)){
				  //remove the key from the root and make changes to the following nodes
				  return root.get().makeItABTree(key, true);
			  } else if(key < root.get().keys.get(i)){
				  //make changes to the following child nodes so that the tree is ab tree
				  return root.get().children.get(i).makeItABTree(key, false);
			  } else {
				  if(i == root.get().keys.size() - 1){
					//make changes to the following child nodes so that the tree is ab tree
					return root.get().children.get(i+1).makeItABTree(key, false);
				  } else if(key < root.get().keys.get(i+1)){
					//make changes to the following child nodes so that the tree is ab tree
					 return root.get().children.get(i).makeItABTree(key, false);
				  } else continue;
			  }
		  }
	  }
	  return false;
  }

  /**
   * Diese Methode wandelt den Baum in das Graphviz-Format um.
   * 
   * @return der Baum im Graphiz-Format
   */
  String dot () {
    StringBuilder sb = new StringBuilder();
    sb.append("digraph {\n");
    sb.append("\tnode [shape=record];\n");
    if (root.isPresent())
      root.get().dot(sb, 0);
    sb.append("}");
    return sb.toString();
  }
}
