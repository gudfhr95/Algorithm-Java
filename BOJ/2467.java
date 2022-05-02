import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

  static int N;
  static int[] liquids = new int[100000];

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    N = in.nextInt();
    for (int i = 0; i < N; i++) {
      liquids[i] = in.nextInt();
    }

    Arrays.sort(liquids, 0, N);

    int[] result = new int[2];
    int minSum = Integer.MAX_VALUE;

    int l = 0;
    int r = N - 1;
    while (l < r) {
      int sum = liquids[l] + liquids[r];

      if (Math.abs(sum) < Math.abs(minSum)) {
        result[0] = liquids[l];
        result[1] = liquids[r];
        minSum = sum;
      }

      if (Math.abs(liquids[l] + liquids[r - 1]) < Math.abs(liquids[l + 1] + liquids[r])) {
        r -= 1;
      } else {
        l += 1;
      }
    }

    out.printf("%d %d", result[0], result[1]);

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