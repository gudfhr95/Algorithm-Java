import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Main {

  static long N, A, B, C, D;

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    N = in.nextLong();
    A = in.nextLong();
    B = in.nextLong();
    C = in.nextLong();
    D = in.nextLong();

    if (A * D > B * C) {
      out.println(solve(C, D, A, B, N));
    } else {
      out.println(solve(A, B, C, D, N));
    }

    out.flush();
  }

  static long solve(long A, long B, long C, long D, long N) {
    long result = (long) 1e+18 + 1;
    long lcm = lcm(A, C);

    long aCount = 0;
    while (A * aCount < lcm) {
      if (N <= A * aCount) {
        if (B * aCount < result) {
          result = B * aCount;
        }
        break;
      }

      long remainder = N - A * aCount;
      long cCount = (remainder - 1) / C + 1;

      long cost = B * aCount + D * cCount;
      if (cost < result) {
        result = cost;
      }

      aCount += 1;
    }

    return result;
  }

  static long gcd(long a, long b) {
    if (b == 0) {
      return a;
    }
    return gcd(b, a % b);
  }

  static long lcm(long a, long b) {
    return (a * b) / gcd(a, b);
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

  public long nextLong() {
    return Long.parseLong(next());
  }
}