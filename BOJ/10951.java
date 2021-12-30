import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Main {

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    while (true) {
      Integer A = in.nextInt();
      Integer B = in.nextInt();

      if (A == null || B == null) {
        break;
      }

      out.println(A + B);
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
        String input = reader.readLine();
        if (input == null) {
          return null;
        }

        tokenizer = new StringTokenizer(input);
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }
    return tokenizer.nextToken();
  }

  public Integer nextInt() {
    String input = next();
    if (input == null) {
      return null;
    }

    return Integer.parseInt(input);
  }
}
