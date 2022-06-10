import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Node implements Comparable<Node> {

  int n, cost;

  public Node(int n, int cost) {
    this.n = n;
    this.cost = cost;
  }

  @Override
  public int compareTo(Node other) {
    return cost - other.cost;
  }
}

class Edge {

  int to, cost;

  public Edge(int to, int cost) {
    this.to = to;
    this.cost = cost;
  }
}

public class Main {

  static int N, M, X;
  static ArrayList<Edge>[] adj = new ArrayList[1001];
  static int[][] dist = new int[1001][1001];

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    N = in.nextInt();
    M = in.nextInt();
    X = in.nextInt();

    for (int i = 1; i <= N; i++) {
      adj[i] = new ArrayList<>();
      Arrays.fill(dist[i], Integer.MAX_VALUE);
    }

    for (int i = 0; i < M; i++) {
      int S = in.nextInt();
      int E = in.nextInt();
      int T = in.nextInt();

      adj[S].add(new Edge(E, T));
    }

    for (int i = 1; i <= N; i++) {
      dijkstra(i);
    }

    int result = 0;
    for (int i = 1; i <= N; i++) {
      result = Math.max(result, dist[i][X] + dist[X][i]);
    }

    out.println(result);

    out.flush();
  }

  static void dijkstra(int start) {
    PriorityQueue<Node> pq = new PriorityQueue<>();

    dist[start][start] = 0;
    pq.add(new Node(start, 0));

    while (!pq.isEmpty()) {
      Node cur = pq.remove();

      if (dist[start][cur.n] < cur.cost) {
        continue;
      }

      for (Edge next : adj[cur.n]) {
        int cost = dist[start][cur.n] + next.cost;
        if (cost < dist[start][next.to]) {
          dist[start][next.to] = cost;
          pq.add(new Node(next.to, cost));
        }
      }
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