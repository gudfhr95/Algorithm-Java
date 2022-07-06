import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

  static int C, n, m;
  static double[][] d = new double[1001][1001];

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    C = in.nextInt();

    while (C-- > 0) {
      n = in.nextInt();
      m = in.nextInt();

      for (double[] d1 : d) {
        Arrays.fill(d1, -1f);
      }

      out.println(up(n, m));
    }

    out.flush();
  }

  static double up(int height, int day) {
    if (day == 0) {
      if (height <= 0) {
        return 1;
      } else {
        return 0;
      }
    }

    if (height <= 0) {
      return 1;
    }

    if (d[height][day] != -1f) {
      return d[height][day];
    }

    double result = 0.75 * up(height - 2, day - 1) + 0.25 * up(height - 1, day - 1);

    return d[height][day] = result;
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