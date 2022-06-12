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

  int to, order;

  public Edge(int to, int order) {
    this.to = to;
    this.order = order;
  }
}

public class Main {

  static int N, M;
  static ArrayList<Edge>[] adj = new ArrayList[100001];

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    N = in.nextInt();
    M = in.nextInt();

    for (int i = 1; i <= N; i++) {
      adj[i] = new ArrayList<>();
    }

    for (int i = 0; i < M; i++) {
      int A = in.nextInt();
      int B = in.nextInt();

      adj[A].add(new Edge(B, i));
      adj[B].add(new Edge(A, i));
    }

    out.println(dijkstra());

    out.flush();
  }

  static long dijkstra() {
    long[] dist = new long[N + 1];

    PriorityQueue<Node> pq = new PriorityQueue<>();
    Arrays.fill(dist, Long.MAX_VALUE);

    dist[1] = 0;
    pq.add(new Node(1, 0));

    while (!pq.isEmpty()) {
      Node cur = pq.remove();

      for (Edge next : adj[cur.n]) {
        long cost = (M + next.order - (cur.cost % M)) % M;

        if (cur.cost + cost < dist[next.to]) {
          dist[next.to] = cur.cost + cost;
          pq.add(new Node(next.to, cur.cost + cost));
        }
      }
    }

    return dist[N] + 1;
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