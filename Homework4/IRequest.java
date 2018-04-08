package com.svilenstefanov.blatt4;
/**
 * Homework 4
 * @author Svilen Stefanov
 */
import java.io.IOException;
import java.io.Serializable;

/**
 * Dieses Interface beschreibt alle gemeinsamen Methoden eines
 * Requests.
 */
public interface IRequest extends Serializable {
  /**
   * Diese Methode entspricht der accept()-Methode des Visitor-Patterns.
   */
  void accept(RequestVisitor v) throws IOException;
  
  /**
   * Diese Methode ermittelt des Schluessel des Requests.
   * @return der Schluessel
   */
  String getKey();
}
