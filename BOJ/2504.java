import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Stack;

public class Main {

  static int calculate(String s) {
    Stack<Character> stack = new Stack<>();

    int result = 0;
    int num = 1;
    for (int i = 0; i < s.length(); i++) {
      if (s.charAt(i) == '(') {
        stack.push(s.charAt(i));
        num *= 2;
      } else if (s.charAt(i) == '[') {
        stack.push(s.charAt(i));
        num *= 3;
      } else if (s.charAt(i) == ')') {
        if (stack.empty() || stack.peek() != '(') {
          return 0;
        }

        if (s.charAt(i - 1) == '(') {
          result += num;
        }

        stack.pop();
        num /= 2;
      } else if (s.charAt(i) == ']') {
        if (stack.empty() || stack.peek() != '[') {
          return 0;
        }

        if (s.charAt(i - 1) == '[') {
          result += num;
        }

        stack.pop();
        num /= 3;
      }
    }

    if (!stack.empty()) {
      return 0;
    }

    return result;
  }

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    String s = in.next();

    out.print(calculate(s));

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