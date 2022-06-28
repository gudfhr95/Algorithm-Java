import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

  static int C, N;
  static String W, T;
  static int[][] d = new int[101][101];
  static ArrayList<String> result = new ArrayList<>();

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    C = in.nextInt();

    while (C-- > 0) {
      W = in.next();
      N = in.nextInt();

      result.clear();

      for (int i = 0; i < N; i++) {
        for (int[] a1 : d) {
          Arrays.fill(a1, -1);
        }

        T = in.next();

        if (match(0, 0) == 1) {
          result.add(T);
        }
      }

      Collections.sort(result);

      for (String s : result) {
        out.println(s);
      }
    }

    out.flush();
  }

  static int match(int i, int j) {
    if (d[i][j] != -1) {
      return d[i][j];
    }

    if (i < W.length() && j < T.length() && (W.charAt(i) == '?' || W.charAt(i) == T.charAt(j))) {
      return d[i][j] = match(i + 1, j + 1);
    }

    if (i == W.length() && j == T.length()) {
      return d[i][j] = 1;
    }

    if (i < W.length() && W.charAt(i) == '*') {
      if (match(i + 1, j) == 1 || (j < T.length() && match(i, j + 1) == 1)) {
        return d[i][j] = 1;
      }
    }

    return d[i][j] = 0;
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