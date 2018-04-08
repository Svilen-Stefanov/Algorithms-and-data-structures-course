/**
 * Die Klasse DynamicStack soll einen Stapel auf
 * Basis der Klasse {@link DynamicArray} implementieren.
 */
public class DynamicStack {
  private DynamicArray dynArr;
  
  /**
   * Dieses Feld speichert die Anzahl der Elemente auf dem Stapel.
   */
  private int length;
  
  public int getLength() {
    return length;
  }
  
  public DynamicStack (int growthFactor, int maxOverhead) {
    dynArr = new DynamicArray(growthFactor, maxOverhead);
    length = 0;
  }
  
  /**
   * Diese Methode legt ein Element auf den Stapel.
   * 
   * @param value das Element, das auf den Stapel gelegt werden soll
   */
  public void pushBack (int value) {
    Interval rangeNew = dynArr.reportUsage(length > 0 ? new NonEmptyInterval(0, length - 1) : new EmptyInterval(), length + 1);
    assert(rangeNew.getFrom() == 0);
    dynArr.set(length, value);
    length++;
  }

  /**
   * Diese Methode nimmt ein Element vom Stapel.
   * @return das entfernte Element
   */
  public int popBack () {
    if (length == 0)
      throw new RuntimeException("popBack(): DynamicArray is empty");
    int value = dynArr.get(length - 1);
    Interval rangeNew = dynArr.reportUsage(length == 1 ? new EmptyInterval() : new NonEmptyInterval(0, length - 2), 0);
    assert(length == 1 ? rangeNew.isEmpty() : rangeNew.getFrom() == 0);
    length--;
    return value;
  }
}