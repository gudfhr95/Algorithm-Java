import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

  static String go(Deque<Integer> deque, String p) {
    boolean reversed = false;
    for (char c : p.toCharArray()) {
      if (c == 'R') {
        reversed = !reversed;
      } else if (c == 'D') {
        if (deque.size() == 0) {
          return "error";
        }

        if (reversed) {
          deque.removeLast();
        } else {
          deque.removeFirst();
        }
      }
    }

    if (reversed) {
      Collections.reverse((List<?>) deque);
    }
    return deque.toString().replaceAll("\\s+", "");
  }

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    int T = in.nextInt();
    for (int t = 0; t < T; t++) {
      String p = in.next();
      int n = in.nextInt();
      Deque<Integer> deque = in.nextDeque();

      out.println(go(deque, p));
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

  public Deque<Integer> nextDeque() {
    String s = next();
    s = s.substring(1, s.length() - 1);

    String[] splited = s.split(",");

    Deque<Integer> deque = new LinkedList<>();
    for (String elem : splited) {
      if (elem.length() == 0) {
        continue;
      }

      deque.add(Integer.parseInt(elem));
    }

    return deque;
  }
}