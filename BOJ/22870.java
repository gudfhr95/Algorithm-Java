import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Node implements Comparable<Node> {

  int n, cost, count;

  public Node(int n, int cost, int count) {
    this.n = n;
    this.cost = cost;
    this.count = count;
  }

  @Override
  public int compareTo(Node other) {
    return cost - other.cost;
  }
}

class Edge implements Comparable<Edge> {

  int to, cost;

  public Edge(int to, int cost) {
    this.to = to;
    this.cost = cost;
  }

  @Override
  public int compareTo(Edge other) {
    return other.to - to;
  }
}

public class Main {

  static int N, M, S, E;
  static ArrayList<Edge>[] adj = new ArrayList[200001];
  static int[] dist = new int[200001];
  static int[] from = new int[200001];
  static int[] count = new int[200001];
  static boolean[] erased = new boolean[200001];

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
      int C = in.nextInt();

      adj[A].add(new Edge(B, C));
      adj[B].add(new Edge(A, C));
    }

    for (int i = 1; i <= N; i++) {
      Collections.sort(adj[i]);
    }

    S = in.nextInt();
    E = in.nextInt();

    int se = dijkstra(S, E);
    int es = dijkstra(E, S);

    out.println(se + es);

    out.flush();
  }

  static int dijkstra(int start, int end) {
    PriorityQueue<Node> pq = new PriorityQueue<>();
    Arrays.fill(dist, Integer.MAX_VALUE);
    Arrays.fill(from, Integer.MAX_VALUE);

    dist[start] = 0;
    count[start] = 0;
    pq.add(new Node(start, 0, 0));

    while (!pq.isEmpty()) {
      Node cur = pq.remove();

      if (dist[cur.n] < cur.cost) {
        continue;
      }

      for (Edge next : adj[cur.n]) {
        if (erased[next.to]) {
          continue;
        }

        int cost = dist[cur.n] + next.cost;
        int cnt = cur.count + 1;

        if (cost == dist[next.to] && cnt >= count[next.to]) {
          from[next.to] = cur.n;
          count[next.to] = cnt;
        } else if (cost < dist[next.to]) {
          dist[next.to] = cost;
          from[next.to] = cur.n;
          count[next.to] = cnt;

          pq.add(new Node(next.to, cost, cnt));
        }
      }
    }

    erase(end);

    return dist[end];
  }

  static void erase(int n) {
    int f = from[n];
    if (f == Integer.MAX_VALUE) {
      return;
    }

    if (f != S && f != E) {
      erased[f] = true;
    }

    erase(f);
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
