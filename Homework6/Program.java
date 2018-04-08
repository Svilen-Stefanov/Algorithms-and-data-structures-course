import java.util.Random;

public class Program {

  public static void main (String[] args) {
    Random r = new Random();
    int n = 4 * 1048576;
    int[] numbers = new int[n];
    for (int i = 0; i < n; i++)
      numbers[i] = r.nextInt();

    long startTime = System.currentTimeMillis();
    Pasquicklina.quicksort(numbers, 8);
    long estimatedTime = System.currentTimeMillis() - startTime;

//    System.out.println(Arrays.toString(numbers));
    for (int i = 1; i < numbers.length; i++) {
      if (numbers[i - 1] > numbers[i])
        throw new RuntimeException(":-( " + i);
    }

    System.out.println("Time: " + estimatedTime);
  }

}
