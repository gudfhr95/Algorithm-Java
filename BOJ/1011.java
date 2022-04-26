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

    long T = in.nextLong();
    while (T-- > 0) {
      long x = in.nextLong();
      long y = in.nextLong();

      out.println(getMoveCount(y - x));
    }

    out.flush();
  }

  static long getMoveCount(long n) {
    long result = 1;
    long distance = 0;

    while (distance < n) {
      distance += (long) Math.ceil(result / (double) 2);
      result += 1;
    }

    return result - 1;
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

  public long nextLong() {
    return Long.parseLong(next());
  }
}