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

  int x, y, k;

  public Pos(int x, int y, int k) {
    this.x = x;
    this.y = y;
    this.k = k;
  }
}

public class Main {

  static int[] dx = {0, 1, 0, -1, 1, 2, 2, 1, -1, -2, -2, -1};
  static int[] dy = {1, 0, -1, 0, 2, 1, -1, -2, -2, -1, 1, 2};
  static int[] dk = {0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1};

  static int K, W, H;
  static int[][] board = new int[200][200];
  static int[][][] dist = new int[200][200][31];

  static void bfs() {
    Queue<Pos> q = new LinkedList<>();
    for (int[][] x : dist) {
      for (int[] y : x) {
        Arrays.fill(y, -1);
      }
    }

    q.add(new Pos(0, 0, 0));
    dist[0][0][0] = 0;

    while (!q.isEmpty()) {
      Pos cur = q.peek();
      q.remove();

      for (int k = 0; k < 12; k++) {
        int xn = cur.x + dx[k];
        int yn = cur.y + dy[k];
        int kn = cur.k + dk[k];

        if (xn < 0 || yn < 0 || xn > W - 1 || yn > H - 1) {
          continue;
        }

        if (board[yn][xn] == 1) {
          continue;
        }

        if (kn > K || dist[yn][xn][kn] != -1) {
          continue;
        }

        q.add(new Pos(xn, yn, kn));
        dist[yn][xn][kn] = dist[cur.y][cur.x][cur.k] + 1;
      }
    }
  }

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    K = in.nextInt();
    W = in.nextInt();
    H = in.nextInt();

    for (int y = 0; y < H; y++) {
      for (int x = 0; x < W; x++) {
        board[y][x] = in.nextInt();
      }
    }

    bfs();

    int result = -1;
    for (int k = 0; k <= K; k++) {
      if (dist[H - 1][W - 1][k] == -1) {
        continue;
      }

      if (result == -1 || dist[H - 1][W - 1][k] < result) {
        result = dist[H - 1][W - 1][k];
      }
    }

    out.println(result);

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