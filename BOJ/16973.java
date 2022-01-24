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

  int x, y;

  public Pos(int x, int y) {
    this.x = x;
    this.y = y;
  }
}

public class Main {

  static int[] dx = {0, 1, 0, -1};
  static int[] dy = {1, 0, -1, 0};

  static int N, M, H, W, sx, sy, fx, fy;
  static int[][] board = new int[1001][1001];
  static int[][] dist = new int[1001][1001];

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    N = in.nextInt();
    M = in.nextInt();

    for (int y = 1; y <= N; y++) {
      for (int x = 1; x <= M; x++) {
        board[y][x] = in.nextInt() + board[y - 1][x] + board[y][x - 1] - board[y - 1][x - 1];
      }
    }

    H = in.nextInt();
    W = in.nextInt();

    sy = in.nextInt();
    sx = in.nextInt();
    fy = in.nextInt();
    fx = in.nextInt();

    bfs(new Pos(sx, sy));

    out.println(dist[fy][fx]);

    out.flush();
  }

  static void bfs(Pos start) {
    for (int[] x : dist) {
      Arrays.fill(x, -1);
    }

    Queue<Pos> q = new LinkedList<>();

    q.add(start);
    dist[start.y][start.x] = 0;

    while (!q.isEmpty()) {
      Pos cur = q.remove();

      for (int k = 0; k < 4; k++) {
        int xn = cur.x + dx[k];
        int yn = cur.y + dy[k];

        if (dist[yn][xn] != -1 || !canMove(xn, yn)) {
          continue;
        }

        q.add(new Pos(xn, yn));
        dist[yn][xn] = dist[cur.y][cur.x] + 1;
      }
    }
  }

  static boolean canMove(int x1, int y1) {
    int x2 = x1 + W - 1;
    int y2 = y1 + H - 1;

    if (x1 < 1 || y1 < 1 || x2 > M || y2 > N) {
      return false;
    }

    int sum = board[y2][x2] - board[y2][x1 - 1] - board[y1 - 1][x2] + board[y1 - 1][x1 - 1];

    return sum == 0;
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