import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Edge {

  int to, dist;

  public Edge(int to, int dist) {
    this.to = to;
    this.dist = dist;
  }
}

public class Main {

  static int N, M;
  static ArrayList<Edge>[] adj = new ArrayList[1001];

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    N = in.nextInt();
    M = in.nextInt();

    for (int i = 1; i <= N; i++) {
      adj[i] = new ArrayList<>();
    }

    for (int i = 0; i < N - 1; i++) {
      int a = in.nextInt();
      int b = in.nextInt();
      int d = in.nextInt();

      adj[a].add(new Edge(b, d));
      adj[b].add(new Edge(a, d));
    }

    for (int i = 0; i < M; i++) {
      int a = in.nextInt();
      int b = in.nextInt();

      out.println(bfs(a, b));
    }

    out.flush();
  }

  static int bfs(int start, int end) {
    Queue<Integer> q = new LinkedList<>();
    int[] dist = new int[N + 1];
    Arrays.fill(dist, -1);

    q.add(start);
    dist[start] = 0;

    while (!q.isEmpty()) {
      int cur = q.remove();

      for (Edge edge : adj[cur]) {
        if (dist[edge.to] != -1) {
          continue;
        }

        q.add(edge.to);
        dist[edge.to] = dist[cur] + edge.dist;
      }
    }

    return dist[end];
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