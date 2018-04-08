package com.svilenstefanov.blatt7b;
public class Program {

  public static void main(String[] args) {
    BinomialHeap bh = new BinomialHeap();
    bh.insert(99);
    bh.insert(22);
    bh.insert(11);
    bh.insert(17);

    System.out.println(bh.deleteMin());
    System.out.println(bh.deleteMin());
    System.out.println(bh.deleteMin());
    System.out.println(bh.deleteMin());
  }

}
