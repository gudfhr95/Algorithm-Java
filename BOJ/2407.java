import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class Main {

  static int n, m;
  static BigInteger[][] d = new BigInteger[101][101];

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    n = in.nextInt();
    m = in.nextInt();

    for (int y = 1; y <= 100; y++) {
      for (int x = 0; x <= y; x++) {
        if (x == 0 || x == y) {
          d[y][x] = BigInteger.ONE;
        } else {
          d[y][x] = BigInteger.ZERO;
        }
      }
    }

    for (int y = 1; y <= 100; y++) {
      for (int x = 0; x <= y; x++) {
        if (d[y][x].compareTo(BigInteger.ZERO) != 0) {
          continue;
        }

        d[y][x] = d[y - 1][x - 1].add(d[y - 1][x]);
      }
    }

    out.println(d[n][m]);

    out.flush();
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