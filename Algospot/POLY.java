import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

  static final int MOD = 10000000;
  static int C, n;
  static int[][] d = new int[101][101];

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    C = in.nextInt();

    while (C-- > 0) {
      n = in.nextInt();

      for (int[] d1 : d) {
        Arrays.fill(d1, -1);
      }

      int result = 0;
      for (int i = 1; i <= n; i++) {
        result += poly(n, i);
        result %= MOD;
      }

      out.println(result);
    }

    out.flush();
  }

  static int poly(int blocks, int first) {
    if (blocks == first) {
      return 1;
    }

    if (d[blocks][first] != -1) {
      return d[blocks][first];
    }

    int result = 0;
    for (int i = 1; i <= blocks - first; i++) {
      result += (first + i - 1) * poly(blocks - first, i);
      result %= MOD;
    }

    return d[blocks][first] = result;
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