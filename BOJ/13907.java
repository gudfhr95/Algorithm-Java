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

public class Main {

  static final int MAX_VALUE = 987654321;

  static int N, M, K, S, D;
  static ArrayList<Integer>[] adj = new ArrayList[1001];
  static int[][] cost = new int[1001][1001];
  static int[][] dist = new int[1001][1001];

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    N = in.nextInt();
    M = in.nextInt();
    K = in.nextInt();
    S = in.nextInt();
    D = in.nextInt();

    for (int i = 1; i <= N; i++) {
      adj[i] = new ArrayList<>();
    }

    for (int i = 0; i < M; i++) {
      int a = in.nextInt();
      int b = in.nextInt();
      int w = in.nextInt();

      adj[a].add(b);
      adj[b].add(a);

      cost[a][b] = cost[b][a] = w;
    }

    dijkstra();

    out.println(getMinCost());

    for (int i = 0; i < K; i++) {
      int p = in.nextInt();

      increaseTax(p);

      out.println(getMinCost());
    }

    out.flush();
  }

  static void dijkstra() {
    PriorityQueue<Node> pq = new PriorityQueue<>();
    for (int[] a1 : dist) {
      Arrays.fill(a1, MAX_VALUE);
    }

    dist[S][0] = 0;
    pq.add(new Node(S, 0, 0));

    while (!pq.isEmpty()) {
      Node cur = pq.remove();

      if (dist[cur.n][cur.count] < cur.cost) {
        continue;
      }

      if (cur.count >= N) {
        continue;
      }

      for (int next : adj[cur.n]) {
        int c = dist[cur.n][cur.count] + cost[cur.n][next];

        if (c < dist[next][cur.count + 1]) {
          dist[next][cur.count + 1] = c;
          pq.add(new Node(next, c, cur.count + 1));
        }
      }
    }
  }

  static int getMinCost() {
    int result = Integer.MAX_VALUE;

    for (int i = 0; i <= N; i++) {
      result = Math.min(result, dist[D][i]);
    }

    return result;
  }

  static void increaseTax(int p) {
    for (int i = 0; i <= N; i++) {
      dist[D][i] += i * p;
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