import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Main {

  static int N;
  static int[] d = new int[50001];

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    N = in.nextInt();

    for (int i = 1; i <= getNearestSquare(N); i++) {
      d[i * i] = 1;
    }

    for (int i = 1; i <= N; i++) {
      if (d[i] != 0) {
        continue;
      }

      int min = Integer.MAX_VALUE;
      for (int j = 1; j <= getNearestSquare(i); j++) {
        min = Math.min(min, 1 + d[i - j * j]);
      }
      d[i] = min;
    }

    out.println(d[N]);

    out.flush();
  }

  static int getNearestSquare(int n) {
    return (int) Math.sqrt(n);
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