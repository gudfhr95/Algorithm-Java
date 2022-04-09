import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

class Pos {

  int x, y;

  Pos(int x, int y) {
    this.x = x;
    this.y = y;
  }
}

class Edge {

  int to, cost;

  Edge(int to, int cost) {
    this.to = to;
    this.cost = cost;
  }
}

class EdgeComparator implements Comparator<Edge> {

  @Override
  public int compare(Edge e1, Edge e2) {
    return e1.cost - e2.cost;
  }
}

public class Main {

  static int[] dx = {0, 1, 0, -1};
  static int[] dy = {-1, 0, 1, 0};

  static int N, M;
  static int[][] map = new int[10][10];
  static int islandCount = 1;
  static ArrayList<Edge>[] adj = new ArrayList[7];

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    N = in.nextInt();
    M = in.nextInt();

    for (int y = 0; y < N; y++) {
      for (int x = 0; x < M; x++) {
        map[y][x] = in.nextInt();
      }
    }

    boolean[][] visited = new boolean[N][M];
    for (int y = 0; y < N; y++) {
      for (int x = 0; x < M; x++) {
        if (visited[y][x] || map[y][x] == 0) {
          continue;
        }

        bfs(x, y, visited);
        islandCount += 1;
      }
    }

    for (int i = 1; i < islandCount; i++) {
      adj[i] = new ArrayList<>();
    }

    for (int y = 0; y < N; y++) {
      for (int x = 0; x < M; x++) {
        if (map[y][x] == 0) {
          continue;
        }

        getDistance(x, y);
      }
    }

    out.println(prim());

    out.flush();
  }

  static void bfs(int x, int y, boolean[][] visited) {
    Queue<Pos> q = new LinkedList<>();

    q.add(new Pos(x, y));
    visited[y][x] = true;

    while (!q.isEmpty()) {
      Pos cur = q.remove();
      map[cur.y][cur.x] = islandCount;

      for (int k = 0; k < 4; k++) {
        int xn = cur.x + dx[k];
        int yn = cur.y + dy[k];

        if (xn < 0 || yn < 0 || xn > M - 1 || yn > N - 1) {
          continue;
        }

        if (visited[yn][xn]) {
          continue;
        }

        if (map[yn][xn] == 0) {
          continue;
        }

        q.add(new Pos(xn, yn));
        visited[yn][xn] = true;
      }
    }
  }

  static void getDistance(int x, int y) {
    for (int k = 0; k < 4; k++) {
      int xn = x + dx[k];
      int yn = y + dy[k];
      int dist = 0;

      while (0 <= xn && xn < M && 0 <= yn && yn < N && map[yn][xn] == 0) {
        xn += dx[k];
        yn += dy[k];
        dist += 1;
      }

      if (xn < 0 || yn < 0 || xn > M - 1 || yn > N - 1) {
        continue;
      }

      if (map[yn][xn] == map[y][x]) {
        continue;
      }

      adj[map[y][x]].add(new Edge(map[yn][xn], dist));
    }
  }

  static int prim() {
    PriorityQueue<Edge> q = new PriorityQueue<>(new EdgeComparator());
    boolean[] visited = new boolean[islandCount];

    for (Edge e : adj[1]) {
      q.add(e);
    }
    visited[1] = true;

    int result = 0;
    while (!q.isEmpty()) {
      Edge cur = q.remove();

      if (visited[cur.to]) {
        continue;
      }

      if (cur.cost == 1) {
        continue;
      }

      for (Edge e : adj[cur.to]) {
        q.add(e);
      }
      visited[cur.to] = true;
      result += cur.cost;
    }

    for (int i = 1; i < islandCount; i++) {
      if (!visited[i]) {
        return -1;
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