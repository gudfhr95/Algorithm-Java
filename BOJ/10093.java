import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Main {

  public static void main(String[] args) {
    InputStream inputStream = System.in;
    OutputStream outputStream = System.out;
    InputReader in = new InputReader(inputStream);
    PrintWriter out = new PrintWriter(outputStream);

    long A = in.nextLong();
    long B = in.nextLong();

    if (A == B) {
      out.print(0);
    } else {
      long max = Math.max(A, B);
      long min = Math.min(A, B);

      out.println(max - min - 1);

      for (long i = min + 1; i < max; i++) {
        out.print(String.format("%d ", i));
      }
    }

    out.flush();
  }
}

class InputReader {

  public BufferedReader reader;
  public StringTokenizer tokenizer;

  public InputReader(InputStream stream) {
    reader = new BufferedReader(new InputStreamReader(stream), 32768);
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
