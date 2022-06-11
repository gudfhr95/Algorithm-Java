import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
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

  int to;
  long cost;

  public Edge(int to, long cost) {
    this.to = to;
    this.cost = cost;
  }
}

public class Main {

  static int N, M, K;
  static ArrayList<Edge>[] adj = new ArrayList[100001];
  static int[] interview = new int[100001];
  static long[] dist = new long[100001];

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    N = in.nextInt();
    M = in.nextInt();
    K = in.nextInt();

    for (int i = 1; i <= N; i++) {
      adj[i] = new ArrayList<>();
      dist[i] = Long.MAX_VALUE;
    }

    for (int i = 0; i < M; i++) {
      int U = in.nextInt();
      int V = in.nextInt();
      int C = in.nextInt();

      adj[V].add(new Edge(U, C));
    }

    for (int i = 0; i < K; i++) {
      interview[i] = in.nextInt();
    }

    dijkstra();

    int index = -1;
    long d = -1;
    for (int i = 1; i <= N; i++) {
      if (dist[i] > d) {
        index = i;
        d = dist[i];
      }
    }

    out.println(index);
    out.println(d);

    out.flush();
  }

  static void dijkstra() {
    PriorityQueue<Node> pq = new PriorityQueue<>();

    for (int i = 0; i < K; i++) {
      dist[interview[i]] = 0;
      pq.add(new Node(interview[i], 0));
    }

    while (!pq.isEmpty()) {
      Node cur = pq.remove();

      if (dist[cur.n] < cur.cost) {
        continue;
      }

      for (Edge next : adj[cur.n]) {
        long cost = dist[cur.n] + next.cost;

        if (cost < dist[next.to]) {
          dist[next.to] = cost;
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