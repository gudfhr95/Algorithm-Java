import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Main {

  static final long MAX_VALUE = 1000000000L;

  static InputReader in = new InputReader(System.in);
  static PrintWriter out = new PrintWriter(System.out);

  static int C, n, m, k;
  static long[][] d = new long[101][101];

  public static void main(String[] args) {

    C = in.nextInt();

    while (C-- > 0) {
      n = in.nextInt();
      m = in.nextInt();
      k = in.nextInt();

      for (int i = 1; i <= n; i++) {
        d[i][0] = 1;
      }

      for (int j = 1; j <= m; j++) {
        d[0][j] = 1;
      }

      for (int i = 1; i <= n; i++) {
        for (int j = 1; j <= m; j++) {
          d[i][j] = Math.min(d[i - 1][j] + d[i][j - 1], MAX_VALUE);
        }
      }

      print(n, m, k);

      out.println();
    }

    out.flush();
  }

  static void print(int l, int s, long count) {
    if (l == 0 && s == 0) {
      return;
    }

    if (l == 0) {
      out.print('o');
      print(l, s - 1, count);
      return;
    } else if (s == 0) {
      out.print('-');
      print(l - 1, s, count);
      return;
    }

    if (count > d[l - 1][s]) {
      out.print('o');
      print(l, s - 1, count - d[l - 1][s]);
    } else {
      out.print('-');
      print(l - 1, s, count);
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
