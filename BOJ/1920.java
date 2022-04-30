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
    for (int i = 0; i < N; i++) {
      A[i] = in.nextInt();
    }

    Arrays.sort(A, 0, N);

    M = in.nextInt();
    while (M-- > 0) {
      if (find(in.nextInt())) {
        out.println(1);
      } else {
        out.println(0);
      }
    }

    out.flush();
  }

  static boolean find(int n) {
    int l = 0;
    int r = N;
    while (l < r) {
      int m = (l + r) / 2;

      if (A[m] == n) {
        return true;
      }

      if (n > A[m]) {
        l = m + 1;
      } else {
        r = m;
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