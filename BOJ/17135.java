import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Main {

  static int N, M, D;
  static int[][] grid = new int[15][15];

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    N = in.nextInt();
    M = in.nextInt();
    D = in.nextInt();

    for (int y = 0; y < N; y++) {
      for (int x = 0; x < M; x++) {
        grid[y][x] = in.nextInt();
      }
    }

    int[] archers = new int[M];
    archers[M - 1] = archers[M - 2] = archers[M - 3] = 1;

    int result = 0;
    do {
      result = Math.max(result, simulate(archers));
    } while (nextPermutation(archers));

    out.println(result);

    out.flush();
  }

  static int simulate(int[] archers) {
    int[][] g = new int[N][M];
    for (int y = 0; y < N; y++) {
      for (int x = 0; x < M; x++) {
        if (grid[y][x] == 1) {
          g[y][x] = 4;
        }
      }
    }

    int result = 0;
    for (int i = 0; i < N; i++) {
      attack(g, archers);
      result += remove(g);
      move(g);
    }

    return result;
  }

  static void attack(int[][] g, int[] archers) {
    for (int i = 0; i < M; i++) {
      if (archers[i] == 0) {
        continue;
      }

      attackOne(g, i);
    }
  }

  static void attackOne(int[][] g, int i) {
    for (int d = 1; d <= D; d++) {
      for (int dx = -d; dx <= d; dx++) {
        for (int dy = -d; dy <= d; dy++) {
          if (Math.abs(dx) + Math.abs(dy) != d) {
            continue;
          }

          int x = i + dx;
          int y = N - dy;

          if (x < 0 || y < 0 || x > M - 1 || y > N - 1) {
            continue;
          }

          if (g[y][x] > 0) {
            g[y][x] -= 1;
            return;
          }
        }
      }
    }
  }

  static int remove(int[][] g) {
    int result = 0;
    for (int y = 0; y < N; y++) {
      for (int x = 0; x < M; x++) {
        if (g[y][x] == 0) {
          continue;
        }

        if (g[y][x] < 4) {
          g[y][x] = 0;
          result += 1;
        }
      }
    }

    return result;
  }

  static void move(int[][] g) {
    for (int y = N - 1; y > 0; y--) {
      g[y] = g[y - 1];
    }
    g[0] = new int[M];
  }

  static boolean nextPermutation(int[] a) {
    int i = a.length - 1;
    while (i > 0 && a[i] <= a[i - 1]) {
      i -= 1;
    }

    if (i <= 0) {
      return false;
    }

    int j = a.length - 1;
    while (a[j] <= a[i - 1]) {
      j -= 1;
    }

    swap(a, i - 1, j);

    j = a.length - 1;
    while (i <= j) {
      swap(a, i++, j--);
    }

    return true;
  }

  static void swap(int[] a, int i, int j) {
    int temp = a[i];
    a[i] = a[j];
    a[j] = temp;
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