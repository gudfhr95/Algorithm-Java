import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

  static int c;
  static String s;
  static int[] d = new int[10001];

  public static void main(String[] args) {
    InputReaer in = new InputReaer(System.in);
    PrintWriter out = new PrintWriter(System.out);

    c = in.nextInt();

    while (c-- > 0) {
      s = in.next();

      Arrays.fill(d, -1);

      out.println(go(0));
    }

    out.flush();
  }

  static int go(int n) {
    if (n == s.length()) {
      return 0;
    }

    if (d[n] != -1) {
      return d[n];
    }

    int result = Integer.MAX_VALUE / 2;
    for (int i = 3; i <= 5; i++) {
      if (n + i <= s.length()) {
        result = Math.min(result, getDifficulty(n, n + i) + go(i + n));
      }
    }

    return d[n] = result;
  }

  static int getDifficulty(int start, int end) {
    String pi = s.substring(start, end);

    boolean isOne = true;
    for (int i = 0; i < pi.length() - 1; i++) {
      if (pi.charAt(i) != pi.charAt(i + 1)) {
        isOne = false;
        break;
      }
    }

    if (isOne) {
      return 1;
    }

    boolean isArithmetic = true;
    int diff = pi.charAt(0) - pi.charAt(1);
    for (int i = 1; i < pi.length() - 1; i++) {
      if (pi.charAt(i) - pi.charAt(i + 1) != diff) {
        isArithmetic = false;
        break;
      }
    }

    if (isArithmetic) {
      if (Math.abs(diff) == 1) {
        return 2;
      } else {
        return 5;
      }
    }

    boolean isAlternating = true;
    for (int i = 0; i < pi.length() - 2; i++) {
      if (pi.charAt(i) != pi.charAt(i + 2)) {
        isAlternating = false;
        break;
      }
    }

    if (isAlternating) {
      return 4;
    }

    return 10;
  }
}

class InputReaer {

  BufferedReader reader;
  StringTokenizer tokenizer;

  public InputReaer(InputStream stream) {
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