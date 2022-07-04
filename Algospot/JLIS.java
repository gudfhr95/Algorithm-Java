import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

  static int c, n, m;
  static int[] A = new int[101];
  static int[] B = new int[101];
  static int[][] d = new int[101][101];

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    c = in.nextInt();

    while (c-- > 0) {
      n = in.nextInt();
      m = in.nextInt();

      A[0] = Integer.MIN_VALUE;
      for (int i = 1; i <= n; i++) {
        A[i] = in.nextInt();
      }

      B[0] = Integer.MIN_VALUE;
      for (int i = 1; i <= m; i++) {
        B[i] = in.nextInt();
      }

      for (int[] a1 : d) {
        Arrays.fill(a1, -1);
      }

      out.println(jlis(0, 0));
    }

    out.flush();
  }

  static int jlis(int a, int b) {
    if (d[a][b] != -1) {
      return d[a][b];
    }

    int result = 0;
    int maxElem = Math.max(A[a], B[b]);

    for (int i = a + 1; i <= n; i++) {
      if (maxElem < A[i]) {
        result = Math.max(result, 1 + jlis(i, b));
      }
    }

    for (int i = b + 1; i <= m; i++) {
      if (maxElem < B[i]) {
        result = Math.max(result, 1 + jlis(a, i));
      }
    }

    return d[a][b] = result;
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