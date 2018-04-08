package com.svilenstefanov.blatt7a;

public interface KeyDescriptor<K> {
  /**
   * Diese Methode gibt die Anzahl der Buckets zurueck, die zum Sortieren
   * in RadixSort verwendet werden sollen (vgl. die Konstante K in den Vorlesungsfolien).
   * @return die Anzahl an Buckets
   */
  int buckets();
  
  /**
   * Diese Methode gibt die Anzahl an Stellen zurueck, die jeder Schluessel
   * beim Sortieren mit RadixSort hat (vgl. die Konstante d in den Vorlesungsfolien).
   * @return die Anzahl an Stellen
   */
  int digits();
  
  /**
   * Diese Methode berechnet den Schluessel an Stelle 'digit' des
   * Elements 'element'.
   * 
   * @param element das Element, von dem der Schluessel berechnet werden soll
   * @param digit die Stelle, mit der der Schluessel berechnet werden soll
   * @return der Schluessel
   */
  int key(K element, int digit);
}
