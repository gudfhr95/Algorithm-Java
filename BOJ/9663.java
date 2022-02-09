import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Main {

  static int[] dx = {1, 1, -1, -1};
  static int[] dy = {1, -1, -1, 1};

  static int N, result = 0;
  static boolean[][] board = new boolean[15][15];

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    N = in.nextInt();

    queen(0);

    out.println(result);

    out.flush();
  }

  static void queen(int y) {
    if (y == N) {
      result += 1;
    }

    for (int x = 0; x < N; x++) {
      if (check(x, y)) {
        board[y][x] = true;
        queen(y + 1);
        board[y][x] = false;
      }
    }
  }

  static boolean check(int x, int y) {
    for (int r = 0; r < N; r++) {
      if (board[r][x]) {
        return false;
      }
    }

    for (int d = 1; d < N; d++) {
      for (int k = 0; k < 4; k++) {
        int xn = x + dx[k] * d;
        int yn = y + dy[k] * d;

        if (xn < 0 || yn < 0 || xn > N - 1 || yn > N - 1) {
          continue;
        }

        if (board[yn][xn]) {
          return false;
        }
      }
    }

    return true;
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