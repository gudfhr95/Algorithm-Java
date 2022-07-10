import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Main {

  static int N, M;
  static int[] A = new int[101];
  static int[] c = new int[101];
  static int[][] d = new int[101][10001];

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    N = in.nextInt();
    M = in.nextInt();

    for (int i = 1; i <= N; i++) {
      A[i] = in.nextInt();
    }

    for (int i = 1; i <= N; i++) {
      c[i] = in.nextInt();
    }

    out.println(deactivate());

    out.flush();
  }

  static int deactivate() {
    for (int i = 1; i <= N; i++) {
      for (int j = 0; j <= 10000; j++) {
        int cost = j - c[i];

        if (cost >= 0) {
          d[i][j] = Math.max(d[i][j], d[i - 1][cost] + A[i]);
        }

        d[i][j] = Math.max(d[i][j], d[i - 1][j]);
      }
    }

    for (int i = 1; i <= 10001; i++) {
      if (d[N][i] >= M) {
        return i;
      }
    }

    return -1;
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