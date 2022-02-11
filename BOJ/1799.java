import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Main {

  static int N;
  static int[][] board = new int[10][10];
  static int[] result = new int[2];
  static boolean[] upper = new boolean[20];
  static boolean[] lower = new boolean[20];

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    N = in.nextInt();

    for (int y = 0; y < N; y++) {
      for (int x = 0; x < N; x++) {
        board[y][x] = in.nextInt();
      }
    }

    bishop(0, 0, 0, 0);
    bishop(1, 0, 1, 0);

    out.println(result[0] + result[1]);

    out.flush();
  }

  static void bishop(int x, int y, int color, int cnt) {
    if (x >= N) {
      y += 1;
      if (x % 2 == 0) {
        x = 1;
      } else {
        x = 0;
      }
    }

    if (y >= N) {
      result[color] = Math.max(result[color], cnt);
      return;
    }

    if (board[y][x] == 1 && !upper[x + y] && !lower[N - 1 + x - y]) {
      upper[x + y] = lower[N - 1 + x - y] = true;
      bishop(x + 2, y, color, cnt + 1);
      upper[x + y] = lower[N - 1 + x - y] = false;
    }

    bishop(x + 2, y, color, cnt);
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