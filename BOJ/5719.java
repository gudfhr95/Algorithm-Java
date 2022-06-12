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

public class Main {

  static final int MAX_VALUE = 1000000;

  static int N, M, S, D;
  static ArrayList<Integer>[] adj = new ArrayList[501];
  static int[][] cost = new int[501][501];
  static ArrayList<Integer>[] from = new ArrayList[501];

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    while (true) {
      N = in.nextInt();
      M = in.nextInt();

      if (N == 0 || M == 0) {
        break;
      }

      for (int y = 0; y < N; y++) {
        adj[y] = new ArrayList<>();

        for (int x = 0; x < N; x++) {
          if (y == x) {
            continue;
          }

          cost[y][x] = MAX_VALUE;
        }
      }

      S = in.nextInt();
      D = in.nextInt();

      for (int i = 0; i < M; i++) {
        int U = in.nextInt();
        int V = in.nextInt();

        adj[U].add(V);
        cost[U][V] = in.nextInt();
      }

      dijkstra();

      erase(D);

      out.println(dijkstra());
    }

    out.flush();
  }

  static int dijkstra() {
    int[] dist = new int[N];

    PriorityQueue<Node> pq = new PriorityQueue<>();
    Arrays.fill(dist, MAX_VALUE);
    for (int i = 0; i < N; i++) {
      from[i] = new ArrayList<>();
    }

    dist[S] = 0;
    pq.add(new Node(S, 0));

    while (!pq.isEmpty()) {
      Node cur = pq.remove();

      if (dist[cur.n] < cur.cost) {
        continue;
      }

      for (int next : adj[cur.n]) {
        int c = dist[cur.n] + cost[cur.n][next];

        if (c == dist[next]) {
          from[next].add(cur.n);
        } else if (c < dist[next]) {
          dist[next] = c;

          from[next].clear();
          from[next].add(cur.n);

          pq.add(new Node(next, c));
        }
      }
    }

    if (dist[D] == MAX_VALUE) {
      return -1;
    }

    return dist[D];
  }

  static void erase(int n) {
    if (n == S) {
      return;
    }

    for (int f : from[n]) {
      if (cost[f][n] == MAX_VALUE) {
        continue;
      }

      cost[f][n] = MAX_VALUE;

      erase(f);
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