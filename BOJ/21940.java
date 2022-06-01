import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

  static int N, M, K;
  static int[][] d = new int[201][201];
  static int[] C = new int[201];

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    N = in.nextInt();
    M = in.nextInt();

    for (int[] a1 : d) {
      Arrays.fill(a1, Integer.MAX_VALUE / 2);
    }

    for (int i = 1; i <= N; i++) {
      d[i][i] = 0;
    }

    for (int i = 0; i < M; i++) {
      int A = in.nextInt();
      int B = in.nextInt();
      int T = in.nextInt();

      d[A][B] = T;
    }

    floyd();

    K = in.nextInt();
    for (int i = 1; i <= K; i++) {
      C[i] = in.nextInt();
    }

    int[] results = new int[N + 1];
    int value = Integer.MAX_VALUE;
    for (int i = 1; i <= N; i++) {
      int temp = Integer.MIN_VALUE;
      for (int j = 1; j <= K; j++) {
        temp = Math.max(temp, d[i][C[j]] + d[C[j]][i]);
      }

      results[i] = temp;
      value = Math.min(value, temp);
    }

    for (int i = 1; i <= N; i++) {
      if (results[i] == value) {
        out.printf("%d ", i);
      }
    }

    out.flush();
  }

  static void floyd() {
    for (int k = 1; k <= N; k++) {
      for (int y = 1; y <= N; y++) {
        for (int x = 1; x <= N; x++) {
          d[y][x] = Math.min(d[y][x], d[y][k] + d[k][x]);
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