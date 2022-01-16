import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

  static int[] board = new int[101];
  static int[] dist = new int[101];

  static void bfs() {
    Queue<Integer> q = new LinkedList<>();

    q.add(1);
    dist[1] = 0;

    while (!q.isEmpty()) {
      int cur = q.peek();
      q.remove();

      for (int k = 1; k <= 6; k++) {
        int next = cur + k;

        if (next > 100) {
          continue;
        }

        if (board[next] != 0) {
          next = board[next];
        }

        if (dist[next] != -1) {
          continue;
        }

        q.add(next);
        dist[next] = dist[cur] + 1;
      }
    }
  }

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    int N = in.nextInt();
    int M = in.nextInt();

    for (int i = 0; i < N; i++) {
      int x = in.nextInt();
      int y = in.nextInt();

      board[x] = y;
    }

    for (int i = 0; i < M; i++) {
      int u = in.nextInt();
      int v = in.nextInt();

      board[u] = v;
    }

    Arrays.fill(dist, -1);

    bfs();

    out.println(dist[100]);

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
