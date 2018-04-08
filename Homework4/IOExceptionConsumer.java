package com.svilenstefanov.blatt4;
/**
 * Homework 4
 * @author Svilen Stefanov
 */
import java.io.IOException;
import java.util.function.Consumer;

/**
 * Dieses {@link FunctionalInterface} implementiert einen
 * {@link Consumer}, der eine {@link IOException} werfen kann.
 */
@FunctionalInterface
public interface IOExceptionConsumer<T> {
  void accept(T t) throws IOException;
  
}
