import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Diese Klasse implementiert einen AVL-Baum.
 */
public class AVLTree {
  /**
   * Diese innere Klasse repräsentiert einen Knoten des AVL-Baumes.
   */
  private static class AVLTreeNode {
    /**
     * Diese Variable enthält den Schlüssel, den der Knoten speichert.
     */
    private int key;

    /**
     * Diese Variable speichert die Balancierung des Knotens - wie in der
     * Vorlesung erklärt - ab. Ein Wert von -1 bedeutet, dass der linke Teilbaum
     * um eins höher ist als der rechte Teilbaum. Ein Wert von 0 bedeutet, dass
     * die beiden Teilbäume gleich hoch sind. Ein Wert von 1 bedeutet, dass der
     * rechte Teilbaum höher ist.
     */
    private int balance = 0;

    /**
     * Diese Variable speichert den linken Teilbaum.
     */
    private Optional<AVLTreeNode> left = Optional.empty();

    public Optional<AVLTreeNode> getLeft () {
      return left;
    }

    public void setLeft (Optional<AVLTreeNode> left) {
      this.left = left;
    }

    /**
     * Diese Variable speichert den rechten Teilbaum.
     */
    private Optional<AVLTreeNode> right = Optional.empty();

    public Optional<AVLTreeNode> getRight () {
      return right;
    }

    public void setRight (Optional<AVLTreeNode> right) {
      this.right = right;
    }

    /*
    private void rebalance (Consumer<AVLTreeNode> replaceChild) {
    }
    */

    public AVLTreeNode (int key) {
      this.key = key;
    }

    public static Optional<AVLTreeNode> optCtor (int key) {
      return Optional.of(new AVLTreeNode(key));
    }

    /**
     * Diese Methode ermittelt die Höhe des Teilbaums unter diesem Knoten.
     * 
     * @return die ermittelte Höhe
     */
    public int height () {
      /*
       * Todo
       */
    }

    /*
     * Todo
     */

    /**
     * Diese Methode wandelt den Baum in das Graphviz-Format um.
     * 
     * @param sb der StringBuilder für die Ausgabe
     * @param from der Index des nächsten Knotens
     * @return der nächste freie Index
     */
    int dot (StringBuilder sb, int from) {
      int mine = from;
      sb.append('\t').append(mine).append(" [label=\"").append(key)
          .append(", b = ").append(balance).append("\"];\n");
      Mutable<Integer> fromWrapped = new Mutable<Integer>(from);
      BiConsumer<Optional<AVLTreeNode>, String> forNode = (node, label) -> {
        if (node.isPresent()) {
          int leftInd = fromWrapped.get() + 1;
          sb.append('\t').append(mine).append(" -> ").append(leftInd)
              .append(" [label=").append(label).append("];\n");
          fromWrapped.set(node.get().dot(sb, leftInd));
        }
      };
      forNode.accept(left, "l");
      forNode.accept(right, "r");
      return fromWrapped.get();
    }

    /**
     * Diese Methode wandelt den Baum in das Graphviz-Format um.
     * 
     * @param sb der StringBuilder für die Ausgabe
     */
    void dot (StringBuilder sb) {
      dot(sb, 0);
    }
  }

  /**
   * Diese Variable speichert die Wurzel des Baumes.
   */
  Optional<AVLTreeNode> root = Optional.empty();

  public AVLTree () {
  }

  /**
   * Diese Methode ist zum Debuggen gedacht und prüft, ob es sich
   * um einen validen AVL-Baum handelt. Dabei werden die folgenden Eigenschaften
   * geprüft:
   * 
   * - Die Höhe des linken Teilbaumes eines Knotens unterscheidet sich von der
   * Höhe des rechten Teilbaumes um höchstens eins.
   * - Die Schlüssel im linken Teilbaum eines Knotens sind kleiner als der oder
   * gleich dem Schlüssel des Knotens.
   * - Die Schlüssel im rechten Teilbaum eines Knotens sind größer als der Schlüssel des Knotens.
   * - Die Balancierung jedes Knoten entspricht der Höhendifferenz der Teilbäume
   * entsprechend der Erklärung in der Vorlesung.
   * 
   * @return 'true' falls der Baum ein valider AVL-Baum ist, 'false' sonst
   */
  public boolean validAVL () {
    /*
     * Todo
     */
  }

  /**
   * Diese Methode fügt einen neuen Schlüssel in den AVL-Baum ein.
   * 
   * @param key der einzufügende Schlüssel
   */
  public void insert (int key) {
    /*
     * Todo
     */
  }

  /**
   * Diese Methode löscht einen Schlüssel aus dem AVL-Baum.
   * 
   * @param key der zu löschende Schlüssel
   * @return 'true' falls der Schlüssel gefunden und gelöscht wurde, 'false' sonst
   */
  public boolean remove (int key) {
    /*
     * Todo
     */
  }

  /**
   * Diese Methode sucht einen Schlüssel im AVL-Baum.
   * 
   * @param key der Schlüssel, der gesucht werden soll
   * @return 'true', falls der Schlüssel gefunden wurde, 'false' sonst
   */
  public boolean find (int key) {
    /*
     * Todo
     */
  }

  /**
   * Diese Methode wandelt den Baum in das Graphviz-Format um.
   * 
   * @return der Baum im Graphiz-Format
   */
  String dot () {
    StringBuilder sb = new StringBuilder();
    sb.append("digraph {\n");
    if (root.isPresent())
      root.get().dot(sb);
    sb.append("}");
    return sb.toString();
  }

  /**
   * Diese Methode wandelt den Baum in das Graphviz-Format um.
   * 
   * @return der Baum im Graphiz-Format
   */
  @Override public String toString () {
    return dot();
  }

}
