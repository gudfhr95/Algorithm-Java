import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

  static int N, M;
  static int[] A = new int[100000];

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    N = in.nextInt();
    M = in.nextInt();
    for (int i = 0; i < N; i++) {
      A[i] = in.nextInt();
    }

    Arrays.sort(A, 0, N);

    int result = Integer.MAX_VALUE;

    int l = 0;
    int r = 1;
    while (l <= r && r < N) {
      int diff = A[r] - A[l];

      if (diff < M) {
        r += 1;
        continue;
      }

      result = Math.min(result, diff);
      l += 1;
    }

    out.println(result);

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