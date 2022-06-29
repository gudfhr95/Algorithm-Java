import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

  static int C, N;
  static int[] A = new int[501];
  static int[] d = new int[501];

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    C = in.nextInt();

    while (C-- > 0) {
      N = in.nextInt();

      for (int i = 1; i <= N; i++) {
        A[i] = in.nextInt();
      }

      Arrays.fill(d, -1);

      int result = 1;
      for (int i = 1; i <= N; i++) {
        result = Math.max(result, lis(i));
      }

      out.println(result);
    }

    out.flush();
  }

  static int lis(int n) {
    if (d[n] != -1) {
      return d[n];
    }

    int result = 1;
    for (int i = n + 1; i <= N; i++) {
      if (A[n] < A[i]) {
        result = Math.max(result, lis(i) + 1);
      }
    }

    return d[n] = result;
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