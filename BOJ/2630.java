import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Main {

  static int[][] paper = new int[128][128];
  static int[] result = {0, 0};

  public static void main(String[] args) {
    InputRedaer in = new InputRedaer(System.in);
    PrintWriter out = new PrintWriter(System.out);

    int N = in.nextInt();
    for (int y = 0; y < N; y++) {
      for (int x = 0; x < N; x++) {
        paper[y][x] = in.nextInt();
      }
    }

    cut(0, 0, N);

    out.println(result[0]);
    out.println(result[1]);

    out.flush();
  }

  static void cut(int x, int y, int n) {
    if (check(x, y, n)) {
      result[paper[y][x]] += 1;
      return;
    }

    int nn = n / 2;
    cut(x, y, nn);
    cut(x + nn, y, nn);
    cut(x, y + nn, nn);
    cut(x + nn, y + nn, nn);
  }

  static boolean check(int x, int y, int n) {
    int color = paper[y][x];
    for (int r = y; r < y + n; r++) {
      for (int c = x; c < x + n; c++) {
        if (paper[r][c] != color) {
          return false;
        }
      }
    }
    return true;
  }
}

class InputRedaer {

  BufferedReader reader;
  StringTokenizer tokenizer;

  public InputRedaer(InputStream stream) {
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