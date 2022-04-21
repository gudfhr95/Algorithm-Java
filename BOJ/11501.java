import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Main {

  static int T, N;
  static int[] A = new int[1000000];

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    T = in.nextInt();
    while (T-- > 0) {
      N = in.nextInt();

      for (int i = 0; i < N; i++) {
        A[i] = in.nextInt();
      }

      long result = 0;
      int max = A[N - 1];
      for (int i = N - 2; i >= 0; i--) {
        if (A[i] > max) {
          max = A[i];
        } else {
          result += max - A[i];
        }
      }

      out.println(result);
    }

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