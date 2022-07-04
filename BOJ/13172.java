import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Main {

  static final long X = 1_000_000_007L;

  static int M, N, S;

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    M = in.nextInt();

    long result = 0;
    while (M-- > 0) {
      N = in.nextInt();
      S = in.nextInt();

      result += power(N, X - 2) * S;
      result %= X;
    }

    out.println(result);

    out.flush();
  }

  static long power(long a, long n) {
    if (n == 0) {
      return 1;
    }

    if (n % 2 == 1) {
      return a * power(a, n - 1) % X;
    }

    long half = power(a, n / 2);

    return half * half % X;
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