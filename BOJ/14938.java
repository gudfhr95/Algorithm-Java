import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

  static int n, m, r;
  static int[] items = new int[101];
  static int[][] d = new int[101][101];

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    n = in.nextInt();
    m = in.nextInt();
    r = in.nextInt();

    for (int i = 1; i <= n; i++) {
      items[i] = in.nextInt();
    }

    for (int[] a1 : d) {
      Arrays.fill(a1, Integer.MAX_VALUE / 2);
    }

    for (int i = 1; i <= n; i++) {
      d[i][i] = 0;
    }

    for (int i = 0; i < r; i++) {
      int a = in.nextInt();
      int b = in.nextInt();
      int l = in.nextInt();

      d[a][b] = d[b][a] = l;
    }

    floyd();

    int result = 0;
    for (int s = 1; s <= n; s++) {
      int count = 0;
      for (int x = 1; x <= n; x++) {
        if (d[s][x] <= m) {
          count += items[x];
        }
      }

      result = Math.max(result, count);
    }

    out.println(result);

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