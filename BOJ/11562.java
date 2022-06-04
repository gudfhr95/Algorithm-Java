import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Main {

  static int n, m, k;
  static int[][] d = new int[251][251];

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
      int u = in.nextInt();
      int v = in.nextInt();
      int b = in.nextInt();

      if (b == 0) {
        d[u][v] = 0;
        d[v][u] = 1;
      } else if (b == 1) {
        d[u][v] = d[v][u] = 0;
      }
    }

    floyd();

    k = in.nextInt();
    for (int i = 0; i < k; i++) {
      int s = in.nextInt();
      int e = in.nextInt();

      out.println(d[s][e]);
    }

    out.flush();
  }

  static void floyd() {
    for (int k = 1; k <= n; k++) {
      for (int y = 1; y <= n; y++) {
        for (int x = 1; x <= n; x++) {
          d[y][x] = Math.min(d[y][x], d[y][k] + d[k][x]);
        }
      }
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