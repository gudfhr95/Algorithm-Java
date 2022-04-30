import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class Main {

  static int N, M, K;
  static BigInteger[][] d = new BigInteger[101][101];
  static String result = "";

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    N = in.nextInt();
    M = in.nextInt();
    K = in.nextInt();

    getCount();

    if (BigInteger.valueOf(K).compareTo(d[N][M]) > 0) {
      out.println(-1);
    } else {
      solve(N, M, K);

      out.println(result);
    }

    out.flush();
  }

  static void getCount() {
    for (int n = 1; n <= N; n++) {
      d[n][0] = BigInteger.ONE;
    }

    for (int m = 1; m <= M; m++) {
      d[0][m] = BigInteger.ONE;
    }

    for (int n = 1; n <= N; n++) {
      for (int m = 1; m <= M; m++) {
        d[n][m] = d[n - 1][m].add(d[n][m - 1]);
      }
    }
  }

  static void solve(int n, int m, long k) {
    if (k == 0 || n == 0 || m == 0) {
      for (int i = 0; i < n; i++) {
        result += "a";
      }
      for (int i = 0; i < m; i++) {
        result += "z";
      }
      return;
    }

    if (BigInteger.valueOf(k).compareTo(d[n - 1][m]) <= 0) {
      result += "a";
      solve(n - 1, m, k);
    } else {
      result += "z";
      solve(n, m - 1, k - d[n - 1][m].intValue());
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