import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    Stack<Integer> s = new Stack<>();

    int N = in.nextInt();

    long result = 0;
    for (int i = 0; i < N; i++) {
      int h = in.nextInt();

      if (s.empty()) {
        s.push(h);
      } else if (s.peek() > h) {
        result += s.size();
        s.push(h);
      } else {
        while (!s.empty() && (s.peek() <= h)) {
          s.pop();
        }
        result += s.size();
        s.push(h);
      }
    }

    out.print(result);

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