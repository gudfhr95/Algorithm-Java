import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Pos {

  int x, y, dir, present;

  public Pos(int x, int y, int dir, int present) {
    this.x = x;
    this.y = y;
    this.dir = dir;
    this.present = present;
  }
}

public class Main {

  static int[] dx = {0, 1, 0, -1};
  static int[] dy = {1, 0, -1, 0};

  static int N, M, xc1, yc1, xc2, yc2;
  static char[][] map = new char[50][50];
  static int[][][][] dist = new int[50][50][4][4];

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    N = in.nextInt();
    M = in.nextInt();

    Queue<Pos> q = new LinkedList<>();
    for (int[][][] a1 : dist) {
      for (int[][] a2 : a1) {
        for (int[] a3 : a2) {
          Arrays.fill(a3, -1);
        }
      }
    }

    xc1 = yc1 = xc2 = yc2 = -1;
    for (int y = 0; y < N; y++) {
      map[y] = in.next().toCharArray();

      for (int x = 0; x < M; x++) {
        if (map[y][x] == 'S') {
          for (int k = 0; k < 4; k++) {
            q.add(new Pos(x, y, k, 0));
            dist[y][x][k][0] = 0;
          }
        } else if (map[y][x] == 'C') {
          if (xc1 == -1 && yc1 == -1) {
            xc1 = x;
            yc1 = y;
          } else {
            xc2 = x;
            yc2 = y;
          }
        }
      }
    }

    out.println(bfs(q));

    out.flush();
  }

  static int bfs(Queue<Pos> q) {
    while (!q.isEmpty()) {
      Pos cur = q.remove();

      if (cur.present == 3) {
        return dist[cur.y][cur.x][cur.dir][cur.present];
      }

      for (int k = 0; k < 4; k++) {
        if (cur.dir == k) {
          continue;
        }

        int xn = cur.x + dx[k];
        int yn = cur.y + dy[k];

        if (xn < 0 || yn < 0 || xn > M - 1 || yn > N - 1) {
          continue;
        }

        if (map[yn][xn] == '#') {
          continue;
        }

        int pn = cur.present;
        if (map[yn][xn] == 'C') {
          if (xn == xc1 && yn == yc1) {
            pn |= 1;
          } else {
            pn |= 2;
          }
        }

        if (dist[yn][xn][k][pn] != -1) {
          continue;
        }

        q.add(new Pos(xn, yn, k, pn));
        dist[yn][xn][k][pn] = dist[cur.y][cur.x][cur.dir][cur.present] + 1;
      }
    }

    return -1;
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