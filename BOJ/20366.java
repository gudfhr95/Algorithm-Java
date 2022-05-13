import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

  static int N;
  static int[] snows = new int[600];

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    N = in.nextInt();
    for (int i = 0; i < N; i++) {
      snows[i] = in.nextInt();
    }

    Arrays.sort(snows, 0, N);

    int result = Integer.MAX_VALUE;
    for (int i = 0; i < N - 3; i++) {
      for (int j = N - 1; j >= i + 3; j--) {
        result = Math.min(result, getMinDiff(i, j));
      }
    }

    out.println(result);

    out.flush();
  }

  static int getMinDiff(int i, int j) {
    int result = Integer.MAX_VALUE;

    int elsa = snows[i] + snows[j];
    int l = i + 1;
    int r = j - 1;
    while (l < r) {
      int anna = snows[l] + snows[r];

      int diff = elsa - anna;
      result = Math.min(result, Math.abs(diff));

      if (diff > 0) {
        l += 1;
      } else {
        r -= 1;
      }
    }

    return result;
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