import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Edge implements Comparable<Edge> {

  int from, to, cost;

  public Edge(int from, int to, int cost) {
    this.from = from;
    this.to = to;
    this.cost = cost;
  }

  @Override
  public int compareTo(Edge other) {
    return cost - other.cost;
  }
}

public class Main {

  static int N;
  static int[][] C = new int[1001][1001];
  static boolean[] visited = new boolean[1001];

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    N = in.nextInt();

    for (int y = 1; y <= N; y++) {
      for (int x = 1; x <= N; x++) {
        C[y][x] = in.nextInt();
      }
    }

    out.println(mst());

    out.flush();
  }

  static long mst() {
    long result = 0;

    PriorityQueue<Edge> pq = new PriorityQueue<>();
    for (int i = 2; i <= N; i++) {
      pq.add(new Edge(1, i, C[1][i]));
    }
    visited[1] = true;

    while (!pq.isEmpty()) {
      Edge cur = pq.remove();

      if (visited[cur.to]) {
        continue;
      }

      result += cur.cost;
      visited[cur.to] = true;

      for (int next = 1; next <= N; next++) {
        if (visited[next]) {
          continue;
        }

        pq.add(new Edge(cur.to, next, C[cur.to][next]));
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