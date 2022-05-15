import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Jewelry implements Comparable<Jewelry> {

  int m, v;

  public Jewelry(int m, int v) {
    this.m = m;
    this.v = v;
  }

  @Override
  public int compareTo(Jewelry other) {
    if (m == other.m) {
      return other.v - v;
    }

    return m - other.m;
  }
}

public class Main {

  static int N, K;
  static PriorityQueue<Jewelry> jewelries = new PriorityQueue<>();
  static ArrayList<Integer> bags = new ArrayList<>();

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    N = in.nextInt();
    K = in.nextInt();

    for (int i = 0; i < N; i++) {
      Jewelry jewelry = new Jewelry(in.nextInt(), in.nextInt());
      jewelries.add(jewelry);
    }

    for (int i = 0; i < K; i++) {
      bags.add(in.nextInt());
    }

    Collections.sort(bags);

    long result = 0;

    PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
    for (int bag : bags) {
      while (!jewelries.isEmpty() && jewelries.peek().m <= bag) {
        Jewelry jewelry = jewelries.remove();
        pq.add(jewelry.v);
      }

      if (!pq.isEmpty()) {
        result += pq.remove();
      }
    }

    out.println(result);

    out.flush();
  }
}

class InputReader {

  BufferedReader reader;
  StringTokenizer tokenizer;

  public InputReader(InputStream stream) {
    reader = new BufferedReader(new InputStreamReader(stream));
    tokenizer = null;
  }

  public String next() {
    while (tokenizer == null || !tokenizer.hasMoreTokens()) {
      try {
        tokenizer = new StringTokenizer(reader.readLine());
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }
    return tokenizer.nextToken();
  }

  public int nextInt() {
    return Integer.parseInt(next());
  }
}