import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Main {

  static int N, kim, lim;

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    N = in.nextInt();
    kim = in.nextInt();
    lim = in.nextInt();

    int result = 1;
    while (true) {
      if (kim % 2 == 1) {
        kim += 1;
      }

      if (lim % 2 == 1) {
        lim += 1;
      }

      if (kim == lim) {
        break;
      }

      kim /= 2;
      lim /= 2;
      result += 1;
    }

    out.println(result);

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