package com.svilenstefanov.blatt4;
/**
 * Homework 4
 * @author Svilen Stefanov
 */
import java.io.IOException;

public class ReadResponse implements IResponse {
  private static final long serialVersionUID = 1L;
  
  private SerializableOptional<Integer> value;
  
  /**
   * Diese Methode gibt den gelesenen Wert zurueck. Gibt
   * es zum gesuchten Schluessel keinen Wert, so wird
   * {@link SerializableOptional}.empty() zurueckgeliefert.
   * 
   * @return der gelesene Wert
   */
  public SerializableOptional<Integer> getValue() {
    return value;
  }
  
  public ReadResponse(int value) {
    this.value = SerializableOptional.of(value);
  }
  
  public ReadResponse() {
    this.value = SerializableOptional.empty();
  }
  
  @Override
  public String toString() {
    return "Read() = " + value;
  }

  @Override
  public void accept(ResponseVisitor v) throws IOException {
    v.visit(this);
  }
}
