import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

  static long A, B;
  static boolean[] prime = new boolean[10000001];

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    A = in.nextLong();
    B = in.nextLong();

    getPrimes();

    long result = 0;
    for (int i = 2; i <= 10000000; i++) {
      if (!prime[i]) {
        continue;
      }

      long almostPrime = i;
      while (i <= B / almostPrime) {
        if (almostPrime * i >= A) {
          result += 1;
        }

        almostPrime *= i;
      }
    }

    out.println(result);

    out.flush();
  }

  static void getPrimes() {
    Arrays.fill(prime, true);
    prime[0] = prime[1] = false;
    for (int i = 2; i <= 10000; i++) {
      if (!prime[i]) {
        continue;
      }

      for (int j = i * i; j <= 10000000; j += i) {
        prime[j] = false;
      }
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

  public long nextLong() {
    return Long.parseLong(next());
  }
}