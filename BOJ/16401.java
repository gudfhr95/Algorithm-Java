import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

  static int M, N;
  static int[] L = new int[1000000];

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    M = in.nextInt();
    N = in.nextInt();
    for (int i = 0; i < N; i++) {
      L[i] = in.nextInt();
    }

    Arrays.sort(L, 0, N);

    out.println(solve());

    out.flush();
  }

  static int solve() {
    int result = 0;

    int l = 0;
    int r = 1000000000;
    while (l <= r) {
      int mid = (l + r) / 2;

      if (canDistribute(mid)) {
        result = Math.max(result, mid);
        l = mid + 1;
      } else {
        r = mid - 1;
      }
    }

    return result;
  }

  static boolean canDistribute(int l) {
    if (l == 0) {
      return false;
    }

    int count = 0;
    for (int i = 0; i < N; i++) {
      count += L[i] / l;
    }

    return count >= M;
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