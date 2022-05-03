import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

  static int N;
  static int[] A = new int[10000];

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    N = in.nextInt();
    for (int i = 0; i < N; i++) {
      A[i] = in.nextInt();
    }

    Arrays.sort(A, 0, N);

    long result = 0;
    for (int i = 0; i < N - 2; i++) {
      for (int j = i + 1; j < N - 1; j++) {
        int sum = A[i] + A[j];

        int lb = lowerBound(-sum, j + 1);
        int up = upperBound(-sum, j + 1);

        result += (up - lb);
      }
    }

    out.println(result);

    out.flush();
  }

  static int lowerBound(int target, int l) {
    int r = N;
    while (l < r) {
      int mid = (l + r) / 2;

      if (target <= A[mid]) {
        r = mid;
      } else {
        l = mid + 1;
      }
    }

    return r;
  }

  static int upperBound(int target, int l) {
    int r = N;
    while (l < r) {
      int mid = (l + r) / 2;

      if (A[mid] <= target) {
        l = mid + 1;
      } else {
        r = mid;
      }
    }

    return r;
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