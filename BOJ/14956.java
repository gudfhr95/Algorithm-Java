import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

class Pos {

  int x, y;

  public Pos(int x, int y) {
    this.x = x;
    this.y = y;
  }
}

public class Main {

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    int n = in.nextInt();
    int m = in.nextInt();

    Pos result = walk(n, m);

    out.printf("%d %d", result.x, result.y);

    out.flush();
  }

  static Pos walk(int n, int m) {
    if (n == 2) {
      if (m == 1) {
        return new Pos(1, 1);
      } else if (m == 2) {
        return new Pos(1, 2);
      } else if (m == 3) {
        return new Pos(2, 2);
      } else {
        return new Pos(2, 1);
      }
    }

    int half = n / 2;
    int dist = half * half;
    if (m <= dist) {
      Pos p = walk(half, m);
      return new Pos(p.y, p.x);
    } else if (m <= dist * 2) {
      Pos p = walk(half, m - dist);
      return new Pos(p.x, p.y + half);
    } else if (m <= dist * 3) {
      Pos p = walk(half, m - dist * 2);
      return new Pos(p.x + half, p.y + half);
    } else {
      Pos p = walk(half, m - dist * 3);
      return new Pos(n - p.y + 1, half - p.x + 1);
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