import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Main {

  static int[] dx = {0, -1, -1, 0, 1, 1, 1, 0, -1};
  static int[] dy = {0, 0, -1, -1, -1, 0, 1, 1, 1};

  static int N;
  static int[][] baskets = new int[50][50];
  static boolean[][] clouds = new boolean[50][50];

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    N = in.nextInt();
    int M = in.nextInt();

    for (int y = 0; y < N; y++) {
      for (int x = 0; x < N; x++) {
        baskets[y][x] = in.nextInt();
      }
    }

    clouds[N - 2][0] = clouds[N - 2][1] = clouds[N - 1][0] = clouds[N - 1][1] = true;

    while (M-- > 0) {
      int d = in.nextInt();
      int s = in.nextInt();

      move(d, s);
      rain();
      duplicateWater();
      cloud();
    }

    int result = 0;
    for (int y = 0; y < N; y++) {
      for (int x = 0; x < N; x++) {
        result += baskets[y][x];
      }
    }

    out.println(result);

    out.flush();
  }

  static void move(int d, int s) {
    boolean[][] result = new boolean[50][50];

    for (int y = 0; y < N; y++) {
      for (int x = 0; x < N; x++) {
        if (!clouds[y][x]) {
          continue;
        }

        int xn = (x + (dx[d] * s) + (N * 50)) % N;
        int yn = (y + (dy[d] * s) + (N * 50)) % N;

        result[yn][xn] = true;
      }
    }

    clouds = result;
  }

  static void rain() {
    for (int y = 0; y < N; y++) {
      for (int x = 0; x < N; x++) {
        if (!clouds[y][x]) {
          continue;
        }

        baskets[y][x] += 1;
      }
    }
  }

  static void duplicateWater() {
    for (int y = 0; y < N; y++) {
      for (int x = 0; x < N; x++) {
        if (!clouds[y][x]) {
          continue;
        }

        int count = 0;
        for (int k = 2; k <= 8; k += 2) {
          int xn = x + dx[k];
          int yn = y + dy[k];

          if (xn < 0 || yn < 0 || xn > N - 1 || yn > N - 1) {
            continue;
          }

          if (baskets[yn][xn] > 0) {
            count += 1;
          }
        }

        baskets[y][x] += count;
      }
    }
  }

  static void cloud() {
    boolean[][] result = new boolean[50][50];

    for (int y = 0; y < N; y++) {
      for (int x = 0; x < N; x++) {
        if (baskets[y][x] >= 2 && !clouds[y][x]) {
          result[y][x] = true;
          baskets[y][x] -= 2;
        } else {
          result[y][x] = false;
        }
      }
    }

    clouds = result;
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