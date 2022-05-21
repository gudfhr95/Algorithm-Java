import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

  static int T, K;
  static PriorityQueue<Long> pq = new PriorityQueue<>();

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    T = in.nextInt();

    while (T-- > 0) {
      K = in.nextInt();

      pq.clear();
      for (int i = 0; i < K; i++) {
        pq.add((long) in.nextInt());
      }

      long result = 0;
      while (pq.size() > 1) {
        long first = pq.remove();
        long second = pq.remove();

        long sum = first + second;
        pq.add(sum);
        result += sum;
      }

      out.println(result);
    }

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