import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Main {

  static int N;
  static long[] result = new long[10];

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    N = in.nextInt();
    if (N < 100) {
      for (int i = 1; i <= N; i++) {
        count(i, 1);
      }
    } else {
      for (int i = 1; i <= 99; i++) {
        count(i, 1);
      }

      for (int i = 1; i < N / 100; i++) {
        for (int j = 0; j <= 9; j++) {
          result[j] += 20;
        }

        count(i, 100);
      }

      for (int i = N / 100 * 100; i <= N; i++) {
        count(i, 1);
      }
    }

    for (int i = 0; i <= 9; i++) {
      out.printf("%d ", result[i]);
    }

    out.flush();
  }

  static void count(int n, int mult) {
    while (n > 0) {
      result[n % 10] += mult;
      n /= 10;
    }
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