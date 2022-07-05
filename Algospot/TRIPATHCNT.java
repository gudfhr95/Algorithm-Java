import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

  static int C, n;
  static int[][] triangle = new int[100][100];
  static int[][] d = new int[100][100];
  static int[][] p = new int[100][100];

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    C = in.nextInt();

    while (C-- > 0) {
      n = in.nextInt();

      for (int y = 0; y < n; y++) {
        for (int x = 0; x <= y; x++) {
          triangle[y][x] = in.nextInt();
        }
      }

      for (int[] d1 : d) {
        Arrays.fill(d1, -1);
      }

      longestPath(0, 0);

      out.println(p[0][0]);
    }

    out.flush();
  }

  static int longestPath(int x, int y) {
    if (y == n) {
      return 0;
    }

    if (x < 0 || x > n - 1) {
      return Integer.MAX_VALUE;
    }

    if (d[y][x] != -1) {
      return d[y][x];
    }

    int l1 = longestPath(x, y + 1);
    int l2 = longestPath(x + 1, y + 1);
    int result = triangle[y][x] + Math.max(l1, l2);
    if (result > d[y][x]) {
      d[y][x] = result;

      if (y == n - 1) {
        p[y][x] = 1;
      } else if (l1 == l2) {
        p[y][x] = (p[y + 1][x] + p[y + 1][x + 1]);
      } else if (l1 > l2) {
        p[y][x] = p[y + 1][x];
      } else {
        p[y][x] = p[y + 1][x + 1];
      }
    }

    return d[y][x];
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