import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
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

  static int h, w;
  static char[][] map = new char[102][102];

  static int[][] bfs(Pos p) {
    int[][] dist = new int[102][102];
    for (int[] x : dist) {
      Arrays.fill(x, -1);
    }

    Deque<Pos> q = new LinkedList<>();

    q.add(p);
    dist[p.y][p.x] = 0;

    while (!q.isEmpty()) {
      Pos cur = q.peekFirst();
      q.remove();

      for (int k = 0; k < 4; k++) {
        int xn = cur.x + dx[k];
        int yn = cur.y + dy[k];

        if (xn < 0 || yn < 0 || xn > w + 1 || yn > h + 1) {
          continue;
        }

        if (map[yn][xn] == '*' || dist[yn][xn] != -1) {
          continue;
        }

        if (map[yn][xn] == '#') {
          q.addLast(new Pos(xn, yn));
          dist[yn][xn] = dist[cur.y][cur.x] + 1;
        } else {
          q.addFirst(new Pos(xn, yn));
          dist[yn][xn] = dist[cur.y][cur.x];
        }
      }
    }

    return dist;
  }

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    int T = in.nextInt();

    while (T-- > 0) {
      h = in.nextInt();
      w = in.nextInt();

      for (char[] x : map) {
        Arrays.fill(x, '.');
      }

      Pos p1 = new Pos(0, 0);
      Pos p2 = null;
      Pos p3 = null;

      for (int y = 1; y <= h; y++) {
        char[] s = in.next().toCharArray();

        for (int x = 1; x <= w; x++) {
          map[y][x] = s[x - 1];

          if (map[y][x] == '$') {
            if (p2 == null) {
              p2 = new Pos(x, y);
            } else {
              p3 = new Pos(x, y);
            }
          }
        }
      }

      int[][] d1 = bfs(p1);
      int[][] d2 = bfs(p2);
      int[][] d3 = bfs(p3);

      int result = 987654321;
      for (int y = 1; y <= h; y++) {
        for (int x = 1; x <= w; x++) {
          if (map[y][x] == '*') {
            continue;
          }

          if (d1[y][x] == -1 || d2[y][x] == -1 || d3[y][x] == -1) {
            continue;
          }

          int sum = d1[y][x] + d2[y][x] + d3[y][x];
          if (map[y][x] == '#') {
            sum -= 2;
          }

          result = Math.min(result, sum);
        }
      }

      out.println(result);
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