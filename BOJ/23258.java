import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Main {

  static int N, Q;
  static int[][][] D = new int[301][301][301];

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    N = in.nextInt();
    Q = in.nextInt();

    for (int y = 1; y <= N; y++) {
      for (int x = 1; x <= N; x++) {
        D[0][y][x] = in.nextInt();

        if (y != x && D[0][y][x] == 0) {
          D[0][y][x] = Integer.MAX_VALUE / 2;
        }
      }
    }

    floyd();

    for (int i = 0; i < Q; i++) {
      int C = in.nextInt();
      int s = in.nextInt();
      int e = in.nextInt();

      if (D[C - 1][s][e] == Integer.MAX_VALUE / 2) {
        out.println(-1);
      } else {
        out.println(D[C - 1][s][e]);
      }
    }

    out.flush();
  }

  static void floyd() {
    for (int k = 1; k <= N; k++) {
      for (int y = 1; y <= N; y++) {
        for (int x = 1; x <= N; x++) {
          D[k][y][x] = Math.min(D[k - 1][y][x], D[k - 1][y][k] + D[k - 1][k][x]);
        }
      }
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