import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Edge implements Comparable<Edge> {

  int from, to;
  double dist;

  public Edge(int from, int to, double dist) {
    this.from = from;
    this.to = to;
    this.dist = dist;
  }

  @Override
  public int compareTo(Edge other) {
    if (dist - other.dist > 0) {
      return 1;
    } else if (dist - other.dist == 0) {
      return 0;
    } else {
      return -1;
    }
  }
}

public class Main {

  static int N, M;
  static int[] X = new int[1001];
  static int[] Y = new int[1001];
  static double[][] dist = new double[1001][1001];

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    N = in.nextInt();
    M = in.nextInt();

    for (int i = 1; i <= N; i++) {
      X[i] = in.nextInt();
      Y[i] = in.nextInt();
    }

    for (int y = 1; y <= N; y++) {
      for (int x = 1; x <= N; x++) {
        dist[y][x] = Math.pow(X[x] - X[y], 2) + Math.pow(Y[x] - Y[y], 2);
        dist[y][x] = Math.sqrt(dist[y][x]);
      }
    }

    for (int i = 0; i < M; i++) {
      int A = in.nextInt();
      int B = in.nextInt();

      dist[A][B] = dist[B][A] = 0;
    }

    out.printf("%.2f", mst());

    out.flush();
  }

  static double mst() {
    PriorityQueue<Edge> pq = new PriorityQueue<>();
    boolean[] visited = new boolean[N + 1];

    visited[1] = true;
    for (int i = 2; i <= N; i++) {
      pq.add(new Edge(1, i, dist[1][i]));
    }

    double result = 0;
    while (!pq.isEmpty()) {
      Edge cur = pq.remove();

      if (visited[cur.to]) {
        continue;
      }

      result += cur.dist;
      visited[cur.to] = true;

      for (int i = 1; i <= N; i++) {
        if (cur.to == i) {
          continue;
        }

        pq.add(new Edge(cur.to, i, dist[cur.to][i]));
      }
    }

    return result;
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