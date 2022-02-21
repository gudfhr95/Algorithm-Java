import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

  static int N;
  static int[][] A = new int[20][20];

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    N = in.nextInt();
    for (int y = 0; y < N; y++) {
      for (int x = 0; x < N; x++) {
        A[y][x] = in.nextInt();
      }
    }

    int result = 987654321;
    for (int d1 = 1; d1 <= N; d1++) {
      for (int d2 = 1; d2 <= N; d2++) {
        for (int y = 0; y < N; y++) {
          for (int x = 0; x < N; x++) {
            if (y + d1 + d2 > N - 1 || x - d1 < 0 || x + d2 > N - 1) {
              continue;
            }

            result = Math.min(result, divide(x, y, d1, d2));
          }
        }
      }
    }

    out.println(result);

    out.flush();
  }

  static int divide(int x, int y, int d1, int d2) {
    int[][] city = new int[20][20];
    for (int r = 0; r < 20; r++) {
      for (int c = 0; c < 20; c++) {
        city[r][c] = A[r][c];
      }
    }

    int[] count = new int[5];

    int x1 = x, x2 = x, dx1 = -1, dx2 = 1;
    for (int r = y; r <= y + d1 + d2; r++) {
      for (int c = x1; c <= x2; c++) {
        count[4] += city[r][c];
        city[r][c] = 0;
      }

      if (x1 == x - d1) {
        dx1 = 1;
      }
      if (x2 == x + d2) {
        dx2 = -1;
      }

      x1 += dx1;
      x2 += dx2;
    }

    for (int r = 0; r <= y + d1 - 1; r++) {
      for (int c = 0; c <= x; c++) {
        count[0] += city[r][c];
        city[r][c] = 0;
      }
    }

    for (int r = 0; r <= y + d2; r++) {
      for (int c = x; c < N; c++) {
        count[1] += city[r][c];
        city[r][c] = 0;
      }
    }

    for (int r = y + d1 - 1; r < N; r++) {
      for (int c = 0; c < x - d1 + d2; c++) {
        count[2] += city[r][c];
        city[r][c] = 0;
      }
    }

    for (int r = y + d2; r < N; r++) {
      for (int c = x - d1 + d2; c < N; c++) {
        count[3] += city[r][c];
        city[r][c] = 0;
      }
    }

    Arrays.sort(count);
    return count[4] - count[0];
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