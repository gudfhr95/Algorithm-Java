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

  int n;
  long cost;

  public Node(int n, long cost) {
    this.n = n;
    this.cost = cost;
  }

  @Override
  public int compareTo(Node other) {
    return (int) (cost - other.cost);
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

  static int N, E, v1, v2;
  static ArrayList<Edge>[] adj = new ArrayList[801];
  static long[][] dist = new long[801][801];

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    N = in.nextInt();
    E = in.nextInt();

    for (int i = 1; i <= N; i++) {
      adj[i] = new ArrayList<>();
      Arrays.fill(dist[i], Integer.MAX_VALUE);
    }

    for (int i = 0; i < E; i++) {
      int a = in.nextInt();
      int b = in.nextInt();
      int c = in.nextInt();

      adj[a].add(new Edge(b, c));
      adj[b].add(new Edge(a, c));
    }

    v1 = in.nextInt();
    v2 = in.nextInt();

    dijkstra(1);
    dijkstra(v1);
    dijkstra(v2);

    long result = Integer.MAX_VALUE;
    result = Math.min(result, dist[1][v1] + dist[v1][v2] + dist[v2][N]);
    result = Math.min(result, dist[1][v2] + dist[v2][v1] + dist[v1][N]);

    if (result > 800000 * 3) {
      out.println(-1);
    } else {
      out.println(result);
    }

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
        long cost = dist[start][cur.n] + next.cost;

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