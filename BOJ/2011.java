import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Main {

  static String password;
  static int[] d = new int[5001];

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    password = in.next();

    init();

    for (int i = 3; i <= password.length(); i++) {
      if (d[i - 1] != 0 && isInRange(getLastNumber(i, 1))) {
        d[i] += d[i - 1];
      }

      if (d[i - 2] != 0 && getLastNumber(i - 1, 1) != 0 && isInRange(getLastNumber(i, 2))) {
        d[i] += d[i - 2];
      }

      d[i] %= 1000000;
    }

    out.println(d[password.length()]);

    out.flush();
  }

  static void init() {
    if (isInRange(getLastNumber(1, 1))) {
      d[1] += 1;
    }

    if (password.length() >= 2) {
      if (isInRange(getLastNumber(1, 1)) && isInRange(getLastNumber(2, 1))) {
        d[2] += 1;
      }

      if (getLastNumber(1, 1) != 0 && isInRange(getLastNumber(2, 2))) {
        d[2] += 1;
      }
    }
  }

  static int getLastNumber(int n, int offset) {
    return Integer.parseInt(password.substring(n - offset, n));
  }

  static boolean isInRange(int n) {
    return 1 <= n && n <= 26;
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
}