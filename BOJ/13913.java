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

  static int[] from = new int[100001];
  static int[] dist = new int[100001];

  static void bfs(int start) {
    Queue<Integer> q = new LinkedList<>();

    q.add(start);
    dist[start] = 0;
    from[start] = start;

    while (!q.isEmpty()) {
      int cur = q.peek();
      q.remove();

      int[] nexts = {cur - 1, cur + 1, 2 * cur};
      for (int next : nexts) {
        if (next < 0 || next > 100000) {
          continue;
        }

        if (dist[next] != -1) {
          continue;
        }

        q.add(next);
        dist[next] = dist[cur] + 1;
        from[next] = cur;
      }
    }
  }

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    int N = in.nextInt();
    int K = in.nextInt();

    Arrays.fill(dist, -1);
    bfs(N);

    out.println(dist[K]);

    int cur = K;
    Stack<Integer> s = new Stack<>();

    s.push(cur);
    while (cur != N) {
      s.push(from[cur]);
      cur = from[cur];
    }

    while (!s.isEmpty()) {
      out.printf("%d ", s.peek());
      s.pop();
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