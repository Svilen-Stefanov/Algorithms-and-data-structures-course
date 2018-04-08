package com.svilenstefanov.blatt4;
/**
 * Homework 4
 * @author Svilen Stefanov
 */
import java.io.IOException;

/**
 * Diese Klasse implementiert den Visitor des Visitor-Patterns
 * fuer Requests.
 */
public class RequestVisitor {
  /**
   * Der Callback, der fuer {@link ReadRequest}-Objekte aufgerufen wird.
   */
  private IOExceptionConsumer<ReadRequest> readCallback;
  
  /**
   * Der Callback, der fuer {@link StoreRequest}-Objekte aufgerufen wird.
   */
  private IOExceptionConsumer<StoreRequest> storeCallback;
  
  public void __(IOExceptionConsumer<ReadRequest> readCallback, IOExceptionConsumer<StoreRequest> storeCallback) {
    this.readCallback = readCallback;
    this.storeCallback = storeCallback;
  }
  
  public void visit(ReadRequest rr) throws IOException {
    readCallback.accept(rr);
  }
  
  public void visit(StoreRequest sr) throws IOException {
    storeCallback.accept(sr);
  }
}
