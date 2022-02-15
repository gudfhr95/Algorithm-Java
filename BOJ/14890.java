import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

  static int N, L;
  static int[][] map = new int[100][100];
  static boolean[] available = new boolean[100];

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    N = in.nextInt();
    L = in.nextInt();

    for (int y = 0; y < N; y++) {
      for (int x = 0; x < N; x++) {
        map[y][x] = in.nextInt();
      }
    }

    int result = 0;
    for (int y = 0; y < N; y++) {
      if (canMove(y)) {
        result += 1;
      }
    }

    rotate();

    for (int y = 0; y < N; y++) {
      if (canMove(y)) {
        result += 1;
      }
    }

    out.println(result);

    out.flush();
  }

  static boolean canMove(int y) {
    Arrays.fill(available, true);

    for (int x = 0; x < N - 1; x++) {
      if (map[y][x] == map[y][x + 1]) {
        continue;
      }

      if (map[y][x + 1] - map[y][x] == 1) {
        if (!putLeft(x, y)) {
          return false;
        }
      } else if (map[y][x + 1] - map[y][x] == -1) {
        if (!putRight(x + 1, y)) {
          return false;
        }
        x += L - 1;
      } else {
        return false;
      }
    }

    return true;
  }

  static boolean putLeft(int x, int y) {
    if (x < L - 1) {
      return false;
    }

    for (int c = x; c > x - L; c--) {
      if (!available[c] || map[y][c] != map[y][x]) {
        return false;
      }

      available[c] = false;
    }

    return true;
  }

  static boolean putRight(int x, int y) {
    if (N - x < L) {
      return false;
    }

    for (int c = x; c < x + L; c++) {
      if (!available[c] || map[y][c] != map[y][x]) {
        return false;
      }

      available[c] = false;
    }

    return true;
  }

  static void rotate() {
    int[][] result = new int[N][N];

    for (int y = 0; y < N; y++) {
      for (int x = 0; x < N; x++) {
        result[y][x] = map[N - 1 - x][y];
      }
    }

    map = result;
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