import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

  static int c, n, d, p, t, q;
  static int[][] A = new int[50][50];
  static int[] degree = new int[50];
  static double[][] cache = new double[50][101];

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    c = in.nextInt();

    while (c-- > 0) {
      n = in.nextInt();
      d = in.nextInt();
      p = in.nextInt();

      Arrays.fill(degree, 0);

      for (int y = 0; y < n; y++) {
        for (int x = 0; x < n; x++) {
          A[y][x] = in.nextInt();

          if (A[y][x] == 1) {
            degree[y] += 1;
          }
        }
      }

      for (double[] cache1 : cache) {
        Arrays.fill(cache1, -1);
      }

      t = in.nextInt();
      for (int i = 0; i < t; i++) {
        q = in.nextInt();

        out.printf("%.8f ", go(q, d));
      }

      out.println();
    }

    out.flush();
  }

  static double go(int town, int day) {
    if (day == 0) {
      if (town == p) {
        return 1;
      } else {
        return 0;
      }
    }

    if (cache[town][day] != -1) {
      return cache[town][day];
    }

    double result = 0;
    for (int i = 0; i < n; i++) {
      if (A[town][i] == 0) {
        continue;
      }

      result += go(i, day - 1) / degree[i];
    }

    return cache[town][day] = result;
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