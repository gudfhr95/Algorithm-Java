import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Well implements Comparable<Well> {

  int to, cost;

  public Well(int to, int cost) {
    this.to = to;
    this.cost = cost;
  }

  @Override
  public int compareTo(Well other) {
    return cost - other.cost;
  }
}

public class Main {

  static int N;
  static int[] W = new int[301];
  static int[][] P = new int[301][301];
  static boolean[] visited = new boolean[301];

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    N = in.nextInt();

    for (int i = 1; i <= N; i++) {
      W[i] = in.nextInt();
    }

    for (int y = 1; y <= N; y++) {
      for (int x = 1; x <= N; x++) {
        P[y][x] = in.nextInt();
      }
    }

    out.println(mst());

    out.flush();
  }

  static int mst() {
    int result = 0;

    PriorityQueue<Well> pq = new PriorityQueue<>();
    for (int i = 1; i <= N; i++) {
      pq.add(new Well(i, W[i]));
    }

    while (!pq.isEmpty()) {
      Well cur = pq.remove();

      if (visited[cur.to]) {
        continue;
      }

      result += cur.cost;
      visited[cur.to] = true;

      for (int next = 1; next <= N; next++) {
        if (next == cur.to || visited[next]) {
          continue;
        }

        pq.add(new Well(next, P[cur.to][next]));
      }
    }

    return result;
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