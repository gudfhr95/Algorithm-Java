import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.TreeSet;

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

  static int N, M, A, B;
  static long C;
  static ArrayList<Edge>[] adj = new ArrayList[100001];
  static TreeSet<Long> candidates = new TreeSet<>();

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    N = (int) in.nextLong();
    M = (int) in.nextLong();
    A = (int) in.nextLong();
    B = (int) in.nextLong();
    C = in.nextLong();

    for (int i = 1; i <= N; i++) {
      adj[i] = new ArrayList<>();
    }

    for (int i = 0; i < M; i++) {
      int U = (int) in.nextLong();
      int V = (int) in.nextLong();
      long C = in.nextLong();

      adj[U].add(new Edge(V, C));
      adj[V].add(new Edge(U, C));

      candidates.add(C);
    }

    ArrayList<Long> cand = new ArrayList<>(candidates);

    long result = -1;
    int l = 0;
    int r = cand.size() - 1;
    while (l <= r) {
      int mid = (l + r) / 2;
      long cost = cand.get(mid);

      if (canGo(cost)) {
        result = cost;
        r = mid - 1;
      } else {
        l = mid + 1;
      }
    }

    out.println(result);

    out.flush();
  }

  static boolean canGo(long maxCost) {
    PriorityQueue<Node> pq = new PriorityQueue<>();
    long[] dist = new long[N + 1];

    Arrays.fill(dist, Long.MAX_VALUE);

    dist[A] = 0;
    pq.add(new Node(A, 0));

    while (!pq.isEmpty()) {
      Node cur = pq.remove();

      if (dist[cur.n] < cur.cost) {
        continue;
      }

      for (Edge next : adj[cur.n]) {
        if (next.cost > maxCost) {
          continue;
        }

        long cost = dist[cur.n] + next.cost;
        if (cost > C) {
          continue;
        }

        if (cost < dist[next.to]) {
          dist[next.to] = cost;
          pq.add(new Node(next.to, cost));
        }
      }
    }

    return dist[B] != Long.MAX_VALUE;
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

  public long nextLong() {
    return Long.parseLong(next());
  }
}