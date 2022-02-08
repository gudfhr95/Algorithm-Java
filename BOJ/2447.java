import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Main {

  static char[][] star = new char[2187][2187];

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    int N = in.nextInt();

    print(0, 0, N);

    for (int y = 0; y < N; y++) {
      for (int x = 0; x < N; x++) {
        out.print(star[y][x]);
      }
      out.println();
    }

    out.flush();
  }

  static void print(int x, int y, int n) {
    if (n == 3) {
      fill(x, y, n, '*');
      star[y + 1][x + 1] = ' ';
      return;
    }

    int nn = n / 3;
    print(x, y, nn);
    print(x + nn, y, nn);
    print(x + 2 * nn, y, nn);
    print(x, y + nn, nn);
    fill(x + nn, y + nn, nn, ' ');
    print(x + 2 * nn, y + nn, nn);
    print(x, y + 2 * nn, nn);
    print(x + nn, y + 2 * nn, nn);
    print(x + 2 * nn, y + 2 * nn, nn);
  }

  static void fill(int x, int y, int n, char val) {
    for (int r = y; r < y + n; r++) {
      for (int c = x; c < x + n; c++) {
        star[r][c] = val;
      }
    }
  }
}

class InputReader {

  BufferedReader reader;
  StringTokenizer tokenizer;

  public InputReader(InputStream stream) {
    reader = new BufferedReader(new InputStreamReader(System.in));
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