import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Main {

  static int N, M, T;
  static int[][] d = new int[301][301];

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    N = in.nextInt();
    M = in.nextInt();
    T = in.nextInt();

    for (int y = 1; y <= N; y++) {
      for (int x = 1; x <= N; x++) {
        if (y == x) {
          continue;
        }

        d[y][x] = Integer.MAX_VALUE;
      }
    }

    for (int i = 0; i < M; i++) {
      int u = in.nextInt();
      int v = in.nextInt();
      int h = in.nextInt();

      d[u][v] = h;
    }

    floyd();

    for (int i = 0; i < T; i++) {
      int s = in.nextInt();
      int e = in.nextInt();

      if (d[s][e] > 1000000) {
        out.println(-1);
      } else {
        out.println(d[s][e]);
      }
    }

    out.flush();
  }

  static void floyd() {
    for (int k = 1; k <= N; k++) {
      for (int y = 1; y <= N; y++) {
        for (int x = 1; x <= N; x++) {
          int max = Math.max(d[y][k], d[k][x]);

          d[y][x] = Math.min(d[y][x], max);
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