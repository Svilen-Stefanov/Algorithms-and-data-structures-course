import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import java.util.Random;

public class MaubaTestCaseGenerator {
  private static Random r = new Random();

  private static void permute (int[] values) {
    for (int i = 0; i < values.length; i++) {
      int j = r.nextInt(values.length);
      if(i == j)
        continue;
      values[i] ^= values[j];
      values[j] ^= values[i];
      values[i] ^= values[j];
    }
  }

  public static void main (String[] args) throws FileNotFoundException,
      UnsupportedEncodingException {

    for (int i = 0; i < 200; i++) {
      PrintWriter in = new PrintWriter("testdata/sample" + i + ".in", "UTF-8");
      PrintWriter out = new PrintWriter("testdata/sample" + i + ".out", "UTF-8");

      try {

        int n = 100;
        int[] values = new int[n];

        int a = r.nextInt(5) + 2;
        int b = 2 * a - 1 + r.nextInt(5);
        ABTree abt = new ABTree(a, b);

        in.println(a);
        in.println(b);

        HashSet<Integer> valuesSeen = new HashSet<>();
        for (int j = 0; j < n; j++) {
          int next;
          do {
            next = r.nextInt();
          } while (valuesSeen.contains(next));
          valuesSeen.add(next);
          values[j] = next;
        }

        for (int j = 0; j < values.length; j++) {
          in.println("insert " + values[j]);
          abt.insert(values[j]);
          out.println(abt.dot());
        }

        permute(values);

        for (int j = 0; j < values.length; j++) {
          in.println("remove " + values[j]);
          if(!abt.remove(values[j]))
            throw new RuntimeException("Unable to remove value :-(");
          out.println(abt.dot());
        }

        in.println("exit");
      } finally {
        in.close();
        out.close();
      }
    }
  }
}
