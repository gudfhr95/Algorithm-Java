import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Main {

  static int N;
  static int[] d = new int[1001];

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    N = in.nextInt();

    d[1] = 1;
    d[2] = 2;
    d[3] = 1;
    d[4] = 1;

    for (int i = 5; i <= N; i++) {
      if (d[i - 1] == 2 || d[i - 3] == 2 || d[i - 4] == 2) {
        d[i] = 1;
      } else {
        d[i] = 2;
      }
    }

    if (d[N] == 1) {
      out.println("SK");
    } else {
      out.println("CY");
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