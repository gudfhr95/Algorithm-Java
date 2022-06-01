import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

class Planet {

  int n, x, y, z;

  public Planet(int n, int x, int y, int z) {
    this.n = n;
    this.x = x;
    this.y = y;
    this.z = z;
  }
}

class Edge {

  int from, to, cost;

  public Edge(int from, int to, int cost) {
    this.from = from;
    this.to = to;
    this.cost = cost;
  }
}

public class Main {

  static int N;
  static Planet[] planets = new Planet[100001];
  static ArrayList<Edge> edges = new ArrayList<>();
  static int[] parent = new int[100001];

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    N = in.nextInt();
    for (int i = 1; i <= N; i++) {
      int x = in.nextInt();
      int y = in.nextInt();
      int z = in.nextInt();

      planets[i] = new Planet(i, x, y, z);
    }

    Arrays.sort(planets, 1, N + 1, Comparator.comparingInt(p -> p.x));
    for (int i = 1; i <= N - 1; i++) {
      int cost = Math.abs(planets[i].x - planets[i + 1].x);
      edges.add(new Edge(planets[i].n, planets[i + 1].n, cost));
    }

    Arrays.sort(planets, 1, N + 1, Comparator.comparingInt(p -> p.y));
    for (int i = 1; i <= N - 1; i++) {
      int cost = Math.abs(planets[i].y - planets[i + 1].y);
      edges.add(new Edge(planets[i].n, planets[i + 1].n, cost));
    }

    Arrays.sort(planets, 1, N + 1, Comparator.comparingInt(p -> p.z));
    for (int i = 1; i <= N - 1; i++) {
      int cost = Math.abs(planets[i].z - planets[i + 1].z);
      edges.add(new Edge(planets[i].n, planets[i + 1].n, cost));
    }

    Collections.sort(edges, Comparator.comparingInt(e -> e.cost));

    for (int i = 1; i <= N; i++) {
      parent[i] = i;
    }

    int result = 0;
    for (Edge e : edges) {
      if (find(e.from) == find(e.to)) {
        continue;
      }

      result += e.cost;
      union(e.from, e.to);
    }

    out.println(result);

    out.flush();
  }

  static int find(int x) {
    if (x == parent[x]) {
      return x;
    }

    return parent[x] = find(parent[x]);
  }

  static void union(int x, int y) {
    x = find(x);
    y = find(y);

    if (x != y) {
      parent[y] = x;
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