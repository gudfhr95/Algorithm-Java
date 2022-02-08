import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Main {

  static InputReader in = new InputReader(System.in);
  static PrintWriter out = new PrintWriter(System.out);

  static char[][] video = new char[64][64];

  public static void main(String[] args) {

    int N = in.nextInt();
    for (int y = 0; y < N; y++) {
      video[y] = in.next().toCharArray();
    }

    out.println(quadTree(0, 0, N));

    out.flush();
  }

  static String quadTree(int x, int y, int n) {
    if (check(x, y, n)) {
      return Character.toString(video[y][x]);
    }

    StringBuilder result = new StringBuilder();
    int nn = n / 2;
    result.append('(');
    result.append(quadTree(x, y, nn));
    result.append(quadTree(x + nn, y, nn));
    result.append(quadTree(x, y + nn, nn));
    result.append(quadTree(x + nn, y + nn, nn));
    result.append(')');

    return result.toString();
  }

  static boolean check(int x, int y, int n) {
    char color = video[y][x];

    for (int r = y; r < y + n; r++) {
      for (int c = x; c < x + n; c++) {
        if (video[r][c] != color) {
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