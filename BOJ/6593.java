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

  int x, y, h;

  public Pos(int x, int y, int h) {
    this.x = x;
    this.y = y;
    this.h = h;
  }
}

public class Main {

  static int[] dx = {0, 1, 0, -1, 0, 0};
  static int[] dy = {1, 0, -1, 0, 0, 0};
  static int[] dh = {0, 0, 0, 0, 1, -1};

  static int L, R, C;
  static char[][][] building = new char[30][30][30];
  static int[][][] dist = new int[30][30][30];
  static Pos start, end;

  static int bfs() {
    Queue<Pos> q = new LinkedList<>();

    q.add(start);
    dist[start.h][start.y][start.x] = 0;

    while (!q.isEmpty()) {
      Pos cur = q.peek();
      q.remove();

      for (int k = 0; k < 6; k++) {
        int xn = cur.x + dx[k];
        int yn = cur.y + dy[k];
        int hn = cur.h + dh[k];

        if (xn < 0 || yn < 0 || hn < 0 || xn > C - 1 || yn > R - 1 || hn > L - 1) {
          continue;
        }

        if (building[hn][yn][xn] == '#' || dist[hn][yn][xn] != -1) {
          continue;
        }

        q.add(new Pos(xn, yn, hn));
        dist[hn][yn][xn] = dist[cur.h][cur.y][cur.x] + 1;
      }
    }

    return dist[end.h][end.y][end.x];
  }

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    while (true) {
      L = in.nextInt();
      R = in.nextInt();
      C = in.nextInt();

      if (L == 0 && R == 0 && C == 0) {
        break;
      }

      for (int h = 0; h < L; h++) {
        for (int y = 0; y < R; y++) {
          building[h][y] = in.next().toCharArray();

          for (int x = 0; x < C; x++) {
            if (building[h][y][x] == 'S') {
              start = new Pos(x, y, h);
            } else if (building[h][y][x] == 'E') {
              end = new Pos(x, y, h);
            }
          }
        }
      }

      for (int[][] x : dist) {
        for (int[] y : x) {
          Arrays.fill(y, -1);
        }
      }

      int result = bfs();
      if (result == -1) {
        out.println("Trapped!");
      } else {
        out.printf("Escaped in %d minute(s).\n", result);
      }
    }

    out.flush();
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