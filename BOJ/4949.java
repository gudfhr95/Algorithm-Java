import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Stack;

public class Main {

  static boolean isBalanced(String s) {
    Stack<Character> stack = new Stack<>();

    for (char c : s.toCharArray()) {
      if (c == '(' || c == '[') {
        stack.push(c);
      } else if (c == ')') {
        if (stack.empty()) {
          return false;
        }

        if (stack.peek() != '(') {
          return false;
        }

        stack.pop();
      } else if (c == ']') {
        if (stack.empty()) {
          return false;
        }

        if (stack.peek() != '[') {
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

    while (true) {
      String s = in.next();
      if (s.equals(".")) {
        break;
      }

      if (isBalanced(s)) {
        out.println("yes");
      } else {
        out.println("no");
      }
    }

    out.flush();
  }
}

class InputReader {

  BufferedReader reader;

  public InputReader(InputStream stream) {
    reader = new BufferedReader(new InputStreamReader(stream));
  }

  public String next() {
    try {
      return reader.readLine();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}