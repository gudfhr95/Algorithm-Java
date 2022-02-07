import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Main {

  static InputReader in = new InputReader(System.in);
  static PrintWriter out = new PrintWriter(System.out);

  public static void main(String[] args) {
    int N = in.nextInt();

    int total = (int) Math.pow(2, N) - 1;

    out.println(total);

    hanoi(N, 1, 2, 3);

    out.flush();
  }

  static void hanoi(int n, int source, int by, int target) {
    if (n == 1) {
      out.printf("%d %d\n", source, target);
      return;
    }

    hanoi(n - 1, source, target, by);
    out.printf("%d %d\n", source, target);
    hanoi(n - 1, by, source, target);
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