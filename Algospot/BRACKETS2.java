import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

  static boolean wellMatched(String s) {
    Stack<Character> stack = new Stack<>();

    for (char c : s.toCharArray()) {
      if (c == '(' || c == '{' || c == '[') {
        stack.push(c);
      } else if (c == ')') {
        if (stack.empty() || stack.peek() != '(') {
          return false;
        }

        stack.pop();
      } else if (c == '}') {
        if (stack.empty() || stack.peek() != '{') {
          return false;
        }

        stack.pop();
      } else if (c == ']') {
        if (stack.empty() || stack.peek() != '[') {
          return false;
        }

        stack.pop();
      }
    }

    return stack.empty();
  }

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    int C = in.nextInt();
    while (C-- > 0) {
      if (wellMatched(in.next())) {
        out.println("YES");
      } else {
        out.println("NO");
      }
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
    while (tokenizer == null || !tokenizer.hasMoreElements()) {
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