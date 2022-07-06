import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Main {

  static final int MOD = 1000000007;

  static int C, n;
  static int[] d = new int[101];

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    d[0] = d[1] = 1;
    d[2] = 2;
    for (int i = 3; i <= 100; i++) {
      d[i] = d[i - 1] + d[i - 2];
      d[i] %= MOD;
    }

    C = in.nextInt();

    while (C-- > 0) {
      n = in.nextInt();

      int result = d[n];
      if (n % 2 == 1) {
        result -= d[n / 2];
        result = (result + MOD) % MOD;
      } else {
        result -= d[n / 2];
        result = (result + MOD) % MOD;

        result -= d[n / 2 - 1];
        result = (result + MOD) % MOD;
      }

      out.println(result);
    }

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