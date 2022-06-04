import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Main {

  static int n, m;
  static int[][] d = new int[201][201];
  static int[][] from = new int[201][201];

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    n = in.nextInt();
    m = in.nextInt();

    for (int y = 1; y <= n; y++) {
      for (int x = 1; x <= n; x++) {
        if (y == x) {
          continue;
        }

        d[y][x] = Integer.MAX_VALUE / 2;
      }
    }

    for (int i = 0; i < m; i++) {
      int s = in.nextInt();
      int e = in.nextInt();
      int c = in.nextInt();

      d[s][e] = d[e][s] = c;
      from[s][e] = e;
      from[e][s] = s;
    }

    floyd();

    for (int y = 1; y <= n; y++) {
      for (int x = 1; x <= n; x++) {
        if (from[y][x] == 0) {
          out.printf("- ");
        } else {
          out.printf("%d ", getFrom(y, x));
        }
      }

      out.println();
    }

    out.flush();
  }

  static void floyd() {
    for (int k = 1; k <= n; k++) {
      for (int y = 1; y <= n; y++) {
        for (int x = 1; x <= n; x++) {
          int sum = d[y][k] + d[k][x];
          if (sum < d[y][x]) {
            d[y][x] = sum;
            from[y][x] = k;
          }
        }
      }
    }
  }

  static int getFrom(int x, int y) {
    while (y != from[x][y]) {
      y = from[x][y];
    }

    return y;
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