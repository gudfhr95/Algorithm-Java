import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Main {

  static int T, W;
  static int[] plum = new int[1001];
  static int[][][] d = new int[1001][3][31];

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    T = in.nextInt();
    W = in.nextInt();

    for (int i = 1; i <= T; i++) {
      plum[i] = in.nextInt();
    }

    if (plum[1] == 1) {
      d[1][1][0] = 1;
    } else {
      d[1][2][1] = 1;
    }

    for (int t = 2; t <= T; t++) {
      for (int p = 1; p <= 2; p++) {
        d[t][p][0] = d[t - 1][p][0];
        if (p == plum[t]) {
          d[t][p][0] += 1;
        }
      }
    }

    for (int t = 2; t <= T; t++) {
      for (int p = 1; p <= 2; p++) {
        for (int w = 1; w <= W; w++) {
          d[t][p][w] = Math.max(d[t - 1][p][w], d[t - 1][3 - p][w - 1]);
          if (p == plum[t]) {
            d[t][p][w] += 1;
          }
        }
      }
    }

    int result = 0;
    for (int p = 1; p <= 2; p++) {
      for (int w = 0; w <= W; w++) {
        result = Math.max(result, d[T][p][w]);
      }
    }

    out.println(result);

    out.flush();
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