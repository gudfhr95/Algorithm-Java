import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Pos implements Comparable<Pos> {

  int x, y;

  public Pos(int x, int y) {
    this.x = x;
    this.y = y;
  }

  @Override
  public int compareTo(Pos o) {
    if (y == o.y) {
      return x - o.x;
    }

    return y - o.y;
  }
}

public class Main {

  static final int[] dx = {0, 1, 0, -1};
  static final int[] dy = {-1, 0, 1, 0};

  static int N, M, fuel;
  static int[][] map = new int[21][21];
  static Pos taxi = null;
  static Pos[] start = new Pos[401];
  static Pos[] end = new Pos[401];

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    N = in.nextInt();
    M = in.nextInt();
    fuel = in.nextInt();

    for (int y = 1; y <= N; y++) {
      for (int x = 1; x <= N; x++) {
        map[y][x] = in.nextInt();
      }
    }

    int ty = in.nextInt();
    int tx = in.nextInt();
    taxi = new Pos(tx, ty);

    for (int i = 1; i <= M; i++) {
      int sy = in.nextInt();
      int sx = in.nextInt();
      int ey = in.nextInt();
      int ex = in.nextInt();

      start[i] = new Pos(sx, sy);
      end[i] = new Pos(ex, ey);
    }

    while (true) {
      boolean canDrive = drive();

      if (!canDrive) {
        break;
      }
    }

    out.println(fuel);

    out.flush();
  }

  static boolean drive() {
    int[][] dist = bfs();

    int target = getTarget(dist);
    if (target == -1) {
      for (int i = 1; i <= M; i++) {
        if (start[i] != null) {
          fuel = -1;
        }
      }
      return false;
    }

    Pos targetStart = start[target];

    fuel -= dist[targetStart.y][targetStart.x];
    if (fuel < 0) {
      fuel = -1;
      return false;
    }

    taxi.x = targetStart.x;
    taxi.y = targetStart.y;
    start[target] = null;

    dist = bfs();

    Pos targetEnd = end[target];
    if (dist[targetEnd.y][targetEnd.x] == -1) {
      fuel = -1;
      return false;
    }

    fuel -= dist[targetEnd.y][targetEnd.x];
    if (fuel < 0) {
      fuel = -1;
      return false;
    }

    taxi.x = targetEnd.x;
    taxi.y = targetEnd.y;
    fuel += dist[targetEnd.y][targetEnd.x] * 2;
    end[target] = null;

    return true;
  }

  static int[][] bfs() {
    int[][] dist = new int[21][21];
    for (int[] a1 : dist) {
      Arrays.fill(a1, -1);
    }

    Queue<Pos> q = new LinkedList<>();

    q.add(taxi);
    dist[taxi.y][taxi.x] = 0;

    while (!q.isEmpty()) {
      Pos cur = q.remove();

      for (int k = 0; k < 4; k++) {
        int xn = cur.x + dx[k];
        int yn = cur.y + dy[k];

        if (xn < 1 || yn < 1 || xn > N || yn > N) {
          continue;
        }

        if (map[yn][xn] == 1 || dist[yn][xn] != -1) {
          continue;
        }

        q.add(new Pos(xn, yn));
        dist[yn][xn] = dist[cur.y][cur.x] + 1;
      }
    }

    return dist;
  }

  static int getTarget(int[][] dist) {
    int target = -1;
    for (int i = 1; i <= M; i++) {
      if (start[i] == null || dist[start[i].y][start[i].x] == -1) {
        continue;
      }

      if (target == -1) {
        target = i;
      }

      Pos targetPos = start[target];
      Pos p = start[i];

      if (dist[p.y][p.x] < dist[targetPos.y][targetPos.x]) {
        target = i;
      } else if (dist[p.y][p.x] == dist[targetPos.y][targetPos.x] && targetPos.compareTo(p) > 0) {
        target = i;
      }
    }

    return target;
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