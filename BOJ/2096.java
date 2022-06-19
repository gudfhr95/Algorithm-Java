import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Main {

  static final int[] dx = {-1, 0, 1};

  static int N;
  static int[][] board = new int[100000][3];
  static int[][] dMax = new int[100000][3];
  static int[][] dMin = new int[100000][3];

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    N = in.nextInt();

    for (int y = 0; y < N; y++) {
      for (int x = 0; x < 3; x++) {
        board[y][x] = in.nextInt();
        dMax[y][x] = Integer.MIN_VALUE;
        dMin[y][x] = Integer.MAX_VALUE;
      }
    }

    go();

    int max = Integer.MIN_VALUE;
    int min = Integer.MAX_VALUE;
    for (int x = 0; x < 3; x++) {
      max = Math.max(max, dMax[N - 1][x]);
      min = Math.min(min, dMin[N - 1][x]);
    }

    out.printf("%d %d", max, min);

    out.flush();
  }

  static void go() {
    for (int x = 0; x < 3; x++) {
      dMax[0][x] = dMin[0][x] = board[0][x];
    }

    for (int y = 1; y < N; y++) {
      for (int x = 0; x < 3; x++) {
        for (int k = 0; k < 3; k++) {
          int xn = x + dx[k];

          if (xn < 0 || xn > 2) {
            continue;
          }

          dMax[y][x] = Math.max(dMax[y][x], board[y][x] + dMax[y - 1][xn]);
          dMin[y][x] = Math.min(dMin[y][x], board[y][x] + dMin[y - 1][xn]);
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