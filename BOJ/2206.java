import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Pos {

  int x, y, b;

  public Pos(int x, int y, int b) {
    this.x = x;
    this.y = y;
    this.b = b;
  }
}

public class Main {

  static int[] dx = {0, 1, 0, -1};
  static int[] dy = {1, 0, -1, 0};

  static int N, M;
  static char[][] map = new char[1000][1000];
  static int[][][] dist = new int[1000][1000][2];

  static int bfs() {
    Queue<Pos> q = new LinkedList<>();

    q.add(new Pos(0, 0, 0));
    dist[0][0][0] = 1;

    while (!q.isEmpty()) {
      Pos cur = q.peek();
      q.remove();

      for (int k = 0; k < 4; k++) {
        int xn = cur.x + dx[k];
        int yn = cur.y + dy[k];

        if (xn < 0 || yn < 0 || xn > M - 1 || yn > N - 1) {
          continue;
        }

        if (map[yn][xn] == '0' && dist[yn][xn][cur.b] == 0) {
          q.add(new Pos(xn, yn, cur.b));
          dist[yn][xn][cur.b] = dist[cur.y][cur.x][cur.b] + 1;
        }

        if (map[yn][xn] == '1' && cur.b == 0 && dist[yn][xn][cur.b + 1] == 0) {
          q.add(new Pos(xn, yn, cur.b + 1));
          dist[yn][xn][cur.b + 1] = dist[cur.y][cur.x][cur.b] + 1;
        }
      }
    }

    if (dist[N - 1][M - 1][0] == 0 || dist[N - 1][M - 1][1] == 0) {
      return Math.max(dist[N - 1][M - 1][0], dist[N - 1][M - 1][1]);
    } else {
      return Math.min(dist[N - 1][M - 1][0], dist[N - 1][M - 1][1]);
    }
  }

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    N = in.nextInt();
    M = in.nextInt();
    for (int y = 0; y < N; y++) {
      map[y] = in.next().toCharArray();
    }

    int result = bfs();
    if (result == 0) {
      out.println(-1);
    } else {
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