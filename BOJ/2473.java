import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

  static int N;
  static long[] liquids = new long[5000];
  static long[] result = new long[3];
  static long minSum = Long.MAX_VALUE;

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    N = in.nextInt();
    for (int i = 0; i < N; i++) {
      liquids[i] = in.nextInt();
    }

    Arrays.sort(liquids, 0, N);

    for (int i = 0; i < N - 2; i++) {
      mix(i);
    }

    Arrays.sort(result);

    for (int i = 0; i < 3; i++) {
      out.printf("%d ", result[i]);
    }

    out.flush();
  }

  static void mix(int i) {
    int l = i + 1;
    int r = N - 1;

    while (l < r) {
      long sum = liquids[i] + liquids[l] + liquids[r];

      if (Math.abs(sum) < Math.abs(minSum)) {
        minSum = sum;
        result[0] = liquids[i];
        result[1] = liquids[l];
        result[2] = liquids[r];
      }

      long leftSum = liquids[i] + liquids[l + 1] + liquids[r];
      long rightSum = liquids[i] + liquids[l] + liquids[r - 1];
      if (Math.abs(leftSum) < Math.abs(rightSum)) {
        l += 1;
      } else {
        r -= 1;
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