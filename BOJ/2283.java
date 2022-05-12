import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Main {

  static int N, K;
  static int[] length = new int[1000001];

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    N = in.nextInt();
    K = in.nextInt();
    for (int i = 0; i < N; i++) {
      int l = in.nextInt();
      int r = in.nextInt();

      for (int j = l; j < r; j++) {
        length[j] += 1;
      }
    }

    int[] result = solve();

    out.printf("%d %d", result[0], result[1]);

    out.flush();
  }

  static int[] solve() {
    int l = 0, r = 0;
    int sum = 0;
    while (l <= r && r <= 1000000) {
      if (sum == K) {
        return new int[]{l, r};
      }

      if (sum < K) {
        sum += length[r];
        r += 1;
      } else {
        sum -= length[l];
        l += 1;
      }
    }

    return new int[]{0, 0};
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