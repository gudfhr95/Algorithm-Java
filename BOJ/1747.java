import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

  static int N;
  static boolean[] isPrime = new boolean[2000000];

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    getPrimes();

    N = in.nextInt();
    for (int i = N; i < isPrime.length; i++) {
      if (isPrime[i] && isPalindrom(Integer.toString(i))) {
        out.println(i);
        break;
      }
    }

    out.flush();
  }

  static void getPrimes() {
    Arrays.fill(isPrime, true);
    isPrime[0] = isPrime[1] = false;
    for (int i = 2; i < isPrime.length; i++) {
      if (!isPrime[i]) {
        continue;
      }

      for (long j = (long) i * i; j < isPrime.length; j += i) {
        isPrime[(int) j] = false;
      }
    }
  }

  static boolean isPalindrom(String n) {
    if (n.length() <= 1) {
      return true;
    }

    if (n.charAt(0) != n.charAt(n.length() - 1)) {
      return false;
    }

    return isPalindrom(n.substring(1, n.length() - 1));
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