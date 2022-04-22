import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

  static int n, m;

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    n = in.nextInt();
    m = in.nextInt();

    long result = 0L;
    PriorityQueue<Long> pq = new PriorityQueue<>();

    for (int i = 0; i < n; i++) {
      long a = (long) in.nextInt();

      pq.add(a);
      result += a;
    }

    for (int i = 0; i < m; i++) {
      long sum = pq.remove() + pq.remove();

      pq.add(sum);
      pq.add(sum);
      result += sum;
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