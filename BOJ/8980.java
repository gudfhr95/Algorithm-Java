import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Delivery implements Comparable<Delivery> {

  int from, to, amount;

  public Delivery(int from, int to, int amount) {
    this.from = from;
    this.to = to;
    this.amount = amount;
  }

  @Override
  public int compareTo(Delivery other) {
    if (to == other.to) {
      if (from == other.from) {
        return other.amount - amount;
      }
      return from - other.from;
    }
    return to - other.to;
  }
}

public class Main {

  static int N, C, M;
  static PriorityQueue<Delivery> deliveries = new PriorityQueue<>();

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    N = in.nextInt();
    C = in.nextInt();
    M = in.nextInt();

    for (int i = 0; i < M; i++) {
      int from = in.nextInt();
      int to = in.nextInt();
      int amount = in.nextInt();
      deliveries.add(new Delivery(from, to, amount));
    }

    int result = 0;
    int[] weight = new int[N + 1];
    while (!deliveries.isEmpty()) {
      Delivery d = deliveries.remove();

      boolean canDeliver = true;
      for (int t = d.from; t < d.to; t++) {
        d.amount = Math.min(C - weight[t], d.amount);

        if (d.amount == 0) {
          canDeliver = false;
          break;
        }
      }

      if (canDeliver) {
        for (int t = d.from; t < d.to; t++) {
          weight[t] += d.amount;
        }
        result += d.amount;
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