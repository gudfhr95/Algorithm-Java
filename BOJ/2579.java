import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Main {

  static int N;
  static int[] stairs = new int[301];
  static int[][] cache = new int[301][2];

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    int N = in.nextInt();
    for (int i = 1; i <= N; i++) {
      stairs[i] = in.nextInt();
    }

    cache[1][0] = stairs[1];
    for (int i = 2; i <= N; i++) {
      cache[i][1] = stairs[i] + cache[i - 1][0];
      cache[i][0] = stairs[i] + Math.max(cache[i - 2][0], cache[i - 2][1]);
    }

    out.println(Math.max(cache[N][0], cache[N][1]));

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