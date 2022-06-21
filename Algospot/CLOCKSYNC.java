import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Main {

  static final int[][] switches = {
      {0, 1, 2},
      {3, 7, 9, 11},
      {4, 10, 14, 15},
      {0, 4, 5, 6, 7},
      {6, 7, 8, 10, 12},
      {0, 2, 14, 15},
      {3, 14, 15},
      {4, 5, 7, 14, 15},
      {1, 2, 3, 4, 5},
      {3, 4, 5, 9, 13}
  };

  static int C;
  static int[] clocks = new int[16];
  static int result = Integer.MAX_VALUE;

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    C = in.nextInt();

    while (C-- > 0) {
      for (int i = 0; i < 16; i++) {
        clocks[i] = in.nextInt();
      }

      result = Integer.MAX_VALUE;

      go(0, 0);

      if (result == Integer.MAX_VALUE) {
        out.println(-1);
      } else {
        out.println(result);
      }
    }

    out.flush();
  }

  static void go(int n, int count) {
    if (n == 10) {
      for (int i = 0; i < 16; i++) {
        if (clocks[i] % 12 != 0) {
          return;
        }
      }

      result = Math.min(result, count);
      return;
    }

    for (int i = 0; i < 4; i++) {
      push(n, i * 3);

      go(n + 1, count + i);

      push(n, i * (-3));
    }
  }

  static void push(int n, int amount) {
    for (int i = 0; i < switches[n].length; i++) {
      clocks[switches[n][i]] += amount;

      if (clocks[switches[n][i]] >= 12) {
        clocks[switches[n][i]] %= 12;
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

  public int nextInt() {
    return Integer.parseInt(next());
  }
}