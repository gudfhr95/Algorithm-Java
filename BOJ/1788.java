import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Main {

  static int N;
  static int[] d = new int[2000001];

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    N = in.nextInt();

    d[1000000] = 0;
    d[1000001] = 1;
    for (int i = 2; i <= 1000000; i++) {
      int index = i + 1000000;
      d[index] = (d[index - 1] + d[index - 2]) % 1000000000;
    }

    for (int i = -1; i >= -1000000; i--) {
      int index = i + 1000000;
      d[index] = (d[index + 2] - d[index + 1]) % 1000000000;
    }

    int index = N + 1000000;
    if (d[index] > 0) {
      out.println(1);
    } else if (d[index] < 0) {
      out.println(-1);
    } else {
      out.println(0);
    }

    out.println(Math.abs(d[index]));

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
