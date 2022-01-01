import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Stack;
import java.util.StringTokenizer;

class Tower {

  int index;
  int height;

  public Tower(int index, int height) {
    this.index = index;
    this.height = height;
  }
}

public class Main {

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    Stack<Tower> s = new Stack<>();

    int N = in.nextInt();
    for (int i = 1; i <= N; i++) {
      Tower tower = new Tower(i, in.nextInt());

      if (s.empty()) {
        out.print("0 ");
        s.push(tower);
      } else if (s.peek().height > tower.height) {
        out.print(s.peek().index + " ");
        s.push(tower);
      } else {
        while (!s.empty() && (s.peek().height <= tower.height)) {
          s.pop();
        }

        if (s.empty()) {
          out.print("0 ");
          s.push(tower);
        } else {
          out.print(s.peek().index + " ");
          s.push(tower);
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
