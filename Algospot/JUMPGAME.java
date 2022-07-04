import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

  static final int[] dx = {0, 1, 0, -1};
  static final int[] dy = {-1, 0, 1, 0};

  static int C, n;
  static int[][] board = new int[100][100];
  static int[][] d = new int[100][100];

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    C = in.nextInt();

    while (C-- > 0) {
      n = in.nextInt();

      for (int y = 0; y < n; y++) {
        for (int x = 0; x < n; x++) {
          board[y][x] = in.nextInt();
        }
      }

      for (int[] a1 : d) {
        Arrays.fill(a1, -1);
      }

      if (go(0, 0) == 1) {
        out.println("YES");
      } else {
        out.println("NO");
      }
    }

    out.flush();
  }

  static int go(int x, int y) {
    if (x == n - 1 && y == n - 1) {
      return 1;
    }

    if (x < 0 || y < 0 || x > n - 1 || y > n - 1) {
      return 0;
    }

    if (d[y][x] != -1) {
      return d[y][x];
    }

    if (go(x, y + board[y][x]) == 1 || go(x + board[y][x], y) == 1) {
      return d[y][x] = 1;
    }

    return d[y][x] = 0;
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