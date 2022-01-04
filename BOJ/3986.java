import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

  static boolean isGoodWord(String word) {
    Stack<Character> stack = new Stack<>();

    for (char c : word.toCharArray()) {
      if (!stack.empty() && stack.peek() == c) {
        stack.pop();
      } else {
        stack.push(c);
      }
    }

    return stack.empty();
  }

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    int N = in.nextInt();

    int result = 0;
    while (N-- > 0) {
      String word = in.next();

      if (isGoodWord(word)) {
        result += 1;
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