import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    Deque<Integer> q = new LinkedList<>();

    int N = in.nextInt();
    for (int i = 0; i < N; i++) {
      String command = in.next();

      if (command.equals("push")) {
        int x = in.nextInt();
        q.add(x);
      } else if (command.equals("pop")) {
        if (q.isEmpty()) {
          out.println(-1);
        } else {
          out.println(q.poll());
        }
      } else if (command.equals("size")) {
        out.println(q.size());
      } else if (command.equals("empty")) {
        if (q.isEmpty()) {
          out.println(1);
        } else {
          out.println(0);
        }
      } else if (command.equals("front")) {
        if (q.isEmpty()) {
          out.println(-1);
        } else {
          out.println(q.peek());
        }
      } else if (command.equals("back")) {
        if (q.isEmpty()) {
          out.println(-1);
        } else {
          out.println(q.peekLast());
        }
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