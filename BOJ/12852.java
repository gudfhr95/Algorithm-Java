import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

  static int X;
  static int[] cache = new int[1000001];
  static int[] from = new int[1000001];

  static InputReader in = new InputReader(System.in);
  static PrintWriter out = new PrintWriter(System.out);

  public static void main(String[] args) {
    X = in.nextInt();

    Arrays.fill(cache, -1);
    Arrays.fill(from, -1);

    bfs();

    out.println(cache[1]);

    printOrder();

    out.flush();
  }

  static void bfs() {
    Queue<Integer> q = new LinkedList<>();

    q.add(X);
    cache[X] = 0;
    from[X] = 0;

    while (!q.isEmpty()) {
      int cur = q.remove();

      int dist = cache[cur] + 1;
      if (cur % 2 == 0) {
        if (cache[cur / 2] == -1 || cache[cur / 2] > dist) {
          cache[cur / 2] = dist;
          from[cur / 2] = cur;
          q.add(cur / 2);
        }
      }
      if (cur % 3 == 0) {
        if (cache[cur / 3] == -1 || cache[cur / 3] > dist) {
          cache[cur / 3] = dist;
          from[cur / 3] = cur;
          q.add(cur / 3);
        }
      }

      if (cur - 1 >= 1 && cache[cur - 1] == -1 || cache[cur - 1] > dist) {
        cache[cur - 1] = dist;
        from[cur - 1] = cur;
        q.add(cur - 1);
      }
    }
  }

  static void printOrder() {
    Stack<Integer> stack = new Stack<>();
    int x = 1;
    while (x != X) {
      stack.push(x);
      x = from[x];
    }
    stack.push(X);

    while (!stack.isEmpty()) {
      out.print(stack.pop() + " ");
    }
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