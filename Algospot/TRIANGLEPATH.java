import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

  static int C, n;
  static int[][] board = new int[101][101];
  static int[][] d = new int[101][101];

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    C = in.nextInt();

    while (C-- > 0) {
      n = in.nextInt();

      for (int y = 1; y <= n; y++) {
        for (int x = 1; x <= y; x++) {
          board[y][x] = in.nextInt();
        }
      }

      for (int[] a1 : d) {
        Arrays.fill(a1, -1);
      }

      out.println(go(1, 1));
    }

    out.flush();
  }

  static int go(int x, int y) {
    if (y == n + 1) {
      return 0;
    }

    if (d[y][x] != -1) {
      return d[y][x];
    }

    int result = board[y][x] + Math.max(go(x, y + 1), go(x + 1, y + 1));

    return d[y][x] = result;
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