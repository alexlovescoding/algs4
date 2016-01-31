import edu.princeton.cs.algs4.StdIn;

public class Subset {
  
  public static void main(String[] args) {
    RandomizedQueue<String> queue = new RandomizedQueue<String>();
    String[] tokens = StdIn.readAllStrings();
    for (String token : tokens) {
      queue.enqueue(token);
    }
    int num = Integer.parseInt(args[0]);
    for (int i = 0; i < num; i++) {
      System.out.println(queue.dequeue());
    }
  }
}
