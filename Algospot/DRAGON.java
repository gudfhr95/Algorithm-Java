import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Main {

  static final int MAX_VALUE = 1_000_000_001;

  static int c, n, p, l;
  static int[] length = new int[51];

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    length[0] = 1;
    for (int i = 1; i <= 50; i++) {
      length[i] = Math.min(2 + 2 * length[i - 1], MAX_VALUE);
    }

    c = in.nextInt();

    while (c-- > 0) {
      n = in.nextInt();
      p = in.nextInt();
      l = in.nextInt();

      for (int i = p; i < p + l; i++) {
        out.print(dragonCurve("FX", n, i - 1));
      }

      out.println();
    }

    out.flush();
  }

  static char dragonCurve(String s, int g, int index) {
    if (g == 0) {
      return s.charAt(index);
    }

    for (int i = 0; i < s.length(); i++) {
      if (s.charAt(i) == 'X' || s.charAt(i) == 'Y') {
        if (index >= length[g]) {
          index -= length[g];
        } else if (s.charAt(i) == 'X') {
          return dragonCurve("X+YF", g - 1, index);
        } else {
          return dragonCurve("FX-Y", g - 1, index);
        }
      } else if (index > 0) {
        index -= 1;
      } else {
        return s.charAt(i);
      }
    }

    return '1';
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