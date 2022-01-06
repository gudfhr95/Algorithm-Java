import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

  static long nextA(long a) {
    long result = a * 214013 + 2531011;
    result %= 4294967296L;

    return result;
  }

  static long getSignal(long a) {
    return (a % 10000) + 1;
  }

  static int count(int k, int n) {
    Queue<Long> queue = new LinkedList<>();

    long a = 1983;
    long signal = 1984;

    int result = 0;
    long sum = 0;
    for (int i = 0; i < n; i++) {
      sum += signal;
      queue.add(signal);

      if (sum == k) {
        result += 1;

        sum -= queue.peek();
        queue.remove();
      } else if (sum > k) {
        while (sum >= k) {
          if (sum == k) {
            result += 1;
          }

          sum -= queue.peek();
          queue.remove();
        }
      }

      a = nextA(a);
      signal = getSignal(a);
    }

    return result;
  }

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    int C = in.nextInt();
    while (C-- > 0) {
      int K = in.nextInt();
      int N = in.nextInt();

      out.println(count(K, N));
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
