package com.svilenstefanov.blatt4;
/**
 * Homework 4
 * @author Svilen Stefanov
 */
/**
 * Diese Klasse implementiert einen simplen Wrapper für Objekte.
 *
 * @param <T> der Typ der Objekte
 */
public class Mutable<T> {
  private T wrapped;
  
  public T get () {
    return wrapped;
  }
  
  public void set (T wrapped) {
    this.wrapped = wrapped;
  }
  
  public Mutable (T wrapped) {
    this.wrapped = wrapped;
  }
  
  public Mutable () {
    this.wrapped = null;
  }
}
