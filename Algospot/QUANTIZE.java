import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

  static int C, N, S;
  static int[] A = new int[100];
  static int[] sum = new int[100];
  static int[] squaredSum = new int[100];
  static int[][] d = new int[100][11];

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    C = in.nextInt();

    while (C-- > 0) {
      N = in.nextInt();
      S = in.nextInt();

      for (int i = 0; i < N; i++) {
        A[i] = in.nextInt();
      }

      Arrays.sort(A, 0, N);

      sum[0] = A[0];
      squaredSum[0] = A[0] * A[0];
      for (int i = 1; i < N; i++) {
        sum[i] = sum[i - 1] + A[i];
        squaredSum[i] = squaredSum[i - 1] + (A[i] * A[i]);
      }

      for (int[] d1 : d) {
        Arrays.fill(d1, -1);
      }

      out.println(quantize(0, S));
    }

    out.flush();
  }

  static int quantize(int from, int part) {
    if (from == N) {
      return 0;
    }

    if (part == 0) {
      return Integer.MAX_VALUE / 2;
    }

    if (d[from][part] != -1) {
      return d[from][part];
    }

    int result = Integer.MAX_VALUE;
    for (int i = from + 1; i <= N; i++) {
      result = Math.min(result, mse(from, i - 1) + quantize(i, part - 1));
    }

    return d[from][part] = result;
  }

  static int mse(int from, int to) {
    int s = sum[to];
    int ss = squaredSum[to];

    if (from > 0) {
      s -= sum[from - 1];
      ss -= squaredSum[from - 1];
    }

    int m = (int) (0.5 + (double) s / (to - from + 1));

    return ss - (2 * m * s) + (m * m * (to - from + 1));
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