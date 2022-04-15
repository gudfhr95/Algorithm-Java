import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    int T = in.nextInt();
    while (T-- > 0) {
      int N = in.nextInt();
      int M = in.nextInt();

      int[] A = new int[N];
      int[] B = new int[M];

      for (int i = 0; i < N; i++) {
        A[i] = in.nextInt();
      }
      for (int i = 0; i < M; i++) {
        B[i] = in.nextInt();
      }

      Arrays.sort(A);
      Arrays.sort(B);

      int result = 0;

      int aIndex = 0;
      int bIndex = 0;
      while (aIndex < N && bIndex < M) {
        if (A[aIndex] > B[bIndex]) {
          bIndex += 1;
          result += 1;
        } else {
          aIndex += 1;
          if (aIndex < N) {
            result += bIndex;
          }
        }
      }

      if (aIndex < N) {
        result += (N - 1 - aIndex) * M;
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