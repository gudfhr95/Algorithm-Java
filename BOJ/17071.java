import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Pos {

  int x, t;

  public Pos(int x, int t) {
    this.x = x;
    this.t = t;
  }
}

public class Main {

  static int[][] dist = new int[500001][2];

  static void bfs(int start) {
    Queue<Pos> q = new LinkedList<>();

    q.add(new Pos(start, 0));
    dist[start][0] = 0;

    while (!q.isEmpty()) {
      Pos cur = q.peek();
      q.remove();

      int[] nexts = {cur.x + 1, cur.x - 1, cur.x * 2};
      for (int next : nexts) {
        if (next < 0 || next > 500000) {
          continue;
        }

        if (dist[next][1 - cur.t] != -1) {
          continue;
        }

        q.add(new Pos(next, 1 - cur.t));
        dist[next][1 - cur.t] = dist[cur.x][cur.t] + 1;
      }
    }
  }

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    int N = in.nextInt();
    int K = in.nextInt();

    for (int[] x : dist) {
      Arrays.fill(x, -1);
    }
    bfs(N);

    int result = -1;
    for (int i = 0; ; i++) {
      K += i;

      if (K > 500000) {
        break;
      }

      if (dist[K][i % 2] <= i) {
        result = i;
        break;
      }
    }

    out.println(result);

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