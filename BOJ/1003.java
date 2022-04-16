import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Main {

  static int[][] cache = new int[41][2];

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    int T = in.nextInt();
    while (T-- > 0) {
      int N = in.nextInt();

      cache[0][0] = 1;
      cache[0][1] = 0;
      cache[1][0] = 0;
      cache[1][1] = 1;

      fibonacci(N);

      out.printf("%d %d\n", cache[N][0], cache[N][1]);
    }

    out.flush();
  }

  static void fibonacci(int n) {
    for (int i = 2; i <= n; i++) {
      cache[i][0] = cache[i - 1][0] + cache[i - 2][0];
      cache[i][1] = cache[i - 1][1] + cache[i - 2][1];
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