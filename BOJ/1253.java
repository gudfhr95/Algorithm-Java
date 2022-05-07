import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

  static int N;
  static int[] A = new int[2000];

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    N = in.nextInt();
    for (int i = 0; i < N; i++) {
      A[i] = in.nextInt();
    }

    Arrays.sort(A, 0, N);

    int result = 0;
    for (int i = 0; i < N; i++) {
      if (isGood(i)) {
        result += 1;
      }
    }

    out.println(result);

    out.flush();
  }

  static boolean isGood(int i) {
    int l = 0;
    int r = N - 1;

    if (r == i) {
      r -= 1;
    }

    if (l == i) {
      l += 1;
    }

    while (l < r) {
      int sum = A[l] + A[r];

      if (sum == A[i]) {
        return true;
      }

      if (sum > A[i]) {
        r -= 1;
        if (r == i) {
          r -= 1;
        }
      } else {
        l += 1;
        if (l == i) {
          l += 1;
        }
      }
    }

    return false;
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