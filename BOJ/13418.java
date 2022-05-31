import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
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

  static int N, M;
  static ArrayList<Integer>[] adj = new ArrayList[1001];
  static int[][] cost = new int[1001][1001];

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    N = in.nextInt();
    M = in.nextInt();

    for (int i = 0; i <= N; i++) {
      adj[i] = new ArrayList<>();
    }

    for (int i = 0; i < M + 1; i++) {
      int A = in.nextInt();
      int B = in.nextInt();
      int C = in.nextInt();

      adj[A].add(B);
      adj[B].add(A);

      cost[A][B] = cost[B][A] = 1 - C;
    }

    int min = mst(false);
    int max = mst(true);

    out.println(max - min);

    out.flush();
  }

  static int mst(boolean isMax) {
    PriorityQueue<Edge> pq;
    if (isMax) {
      pq = new PriorityQueue<>(Collections.reverseOrder());
    } else {
      pq = new PriorityQueue<>();
    }

    boolean[] visited = new boolean[N + 1];

    for (int next : adj[0]) {
      pq.add(new Edge(0, next, cost[0][next]));
    }
    visited[0] = true;

    int result = 0;
    while (!pq.isEmpty()) {
      Edge cur = pq.remove();

      if (visited[cur.to]) {
        continue;
      }

      visited[cur.to] = true;
      result += cur.cost;

      for (int next : adj[cur.to]) {
        pq.add(new Edge(cur.to, next, cost[cur.to][next]));
      }
    }

    return result * result;
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