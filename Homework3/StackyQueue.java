/**
 * Die Klasse StackyQueue soll eine Warteschlange auf
 * Basis der Klasse {@link DynamicStack} implementieren. Es
 * soll ausschließlich die Klasse {@link DynamicStack} zur
 * Datenspeicherung verwendet werden.
 */
public class StackyQueue {
  private DynamicStack first;
  private DynamicStack second;
  
  /**
   * Diese Methode ermittelt die Länge der Warteschlange.
   * @return die Länge der Warteschlange
   */
  public int getLength() {
    return first.getLength() + second.getLength();
  }
  
  /**
   * Dieser Kontruktor initialisiert eine neue Schlange.
   * 
   * @param growthFactor
   * @param maxOverhead
   */
  public StackyQueue (int growthFactor, int maxOverhead) {
    first = new DynamicStack(growthFactor, maxOverhead);
    second =  new DynamicStack(growthFactor, maxOverhead);
  }
  
  /**
   * Diese Methode reiht ein Element in die Schlange ein.
   * 
   * @param value der einzufügende Wert
   */
  public void enqueue (int value) {
    first.pushBack(value);
  }
  
  /**
   * Diese Methode entfernt ein Element aus der Warteschlange.
   * 
   * @return das entfernte Element
   */
  public int dequeue () {
    if(second.getLength() == 0) {
      while(first.getLength() > 0)
        second.pushBack(first.popBack());
    }
    return second.popBack();
  }
}
