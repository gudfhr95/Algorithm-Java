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

class Pos {

  int x, y;

  public Pos(int x, int y) {
    this.x = x;
    this.y = y;
  }
}

public class Main {

  static int[] dx = {0, 1, 0, -1};
  static int[] dy = {1, 0, -1, 0};

  static int N, sx, sy, ex, ey;
  static char[][] room = new char[50][50];
  static int[][] index = new int[50][50];
  static boolean[][] adj = new boolean[2500][2500];
  static int[] dist = new int[2500];

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    N = in.nextInt();

    ArrayList<Pos> points = new ArrayList<>();
    sx = sy = ex = ey = -1;

    for (int y = 0; y < N; y++) {
      room[y] = in.next().toCharArray();

      for (int x = 0; x < N; x++) {
        if (room[y][x] == '#') {
          if (sx == -1 && sy == -1) {
            sx = x;
            sy = y;
          } else {
            ex = x;
            ey = y;
          }

          points.add(new Pos(x, y));
          index[y][x] = points.size() - 1;
        } else if (room[y][x] == '!') {
          points.add(new Pos(x, y));
          index[y][x] = points.size() - 1;
        }
      }
    }

    getAdjMatrix(points);

    bfs(points);

    out.println(dist[index[ey][ex]] - 1);

    out.flush();
  }

  static void getAdjMatrix(ArrayList<Pos> points) {
    for (int i = 0; i < points.size(); i++) {
      Pos cur = points.get(i);

      for (int k = 0; k < 4; k++) {
        int xn = cur.x + dx[k];
        int yn = cur.y + dy[k];

        while (xn >= 0 && yn >= 0 && xn < N && yn < N) {
          if (room[yn][xn] == '*') {
            break;
          }

          if (room[yn][xn] == '!' || room[yn][xn] == '#') {
            adj[i][index[yn][xn]] = true;
          }

          xn += dx[k];
          yn += dy[k];
        }
      }
    }
  }

  static void bfs(ArrayList<Pos> points) {
    Arrays.fill(dist, -1);

    Queue<Integer> q = new LinkedList<>();

    q.add(index[sy][sx]);
    dist[index[sy][sx]] = 0;

    while (!q.isEmpty()) {
      int cur = q.peek();
      q.remove();

      for (int next = 0; next < points.size(); next++) {
        if (!adj[cur][next] || dist[next] != -1) {
          continue;
        }

        q.add(next);
        dist[next] = dist[cur] + 1;
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
