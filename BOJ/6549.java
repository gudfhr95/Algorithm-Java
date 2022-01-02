import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Stack;
import java.util.StringTokenizer;

class Rectangle {

  long height;
  long count;

  public Rectangle(long height) {
    this.height = height;
    this.count = 1;
  }
}

public class Main {

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    while (true) {
      int n = in.nextInt();

      if (n == 0) {
        break;
      }

      Stack<Rectangle> histogram = new Stack<>();

      long result = 0;
      for (int i = 0; i < n; i++) {
        Rectangle r = new Rectangle(in.nextLong());

        long count = 0;
        while (!histogram.empty() && (histogram.peek().height >= r.height)) {
          count += histogram.peek().count;

          result = Math.max(result, count * histogram.peek().height);
          histogram.pop();
        }

        r.count = count + 1;
        histogram.push(r);
      }

      long count = 0;
      while (!histogram.empty()) {
        count += histogram.peek().count;

        result = Math.max(result, count * histogram.peek().height);
        histogram.pop();
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

  public long nextLong() {
    return Long.parseLong(next());
  }
}