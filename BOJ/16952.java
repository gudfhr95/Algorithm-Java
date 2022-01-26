import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Pos {

  int x, y, n, p;

  public Pos(int x, int y, int n, int p) {
    this.x = x;
    this.y = y;
    this.n = n;
    this.p = p;
  }
}

class Dist implements Comparable<Dist> {

  int d, c;

  public Dist(int d, int c) {
    this.d = d;
    this.c = c;
  }


  @Override
  public int compareTo(Dist o) {
    if (d == o.d) {
      return c - o.c;
    }

    return d - o.d;
  }
}

public class Main {

  static int[] dxk = {1, 2, 2, 1, -1, -2, -2, -1};
  static int[] dyk = {2, 1, -1, -2, -2, -1, 1, 2};
  static int[] dxb = {1, 1, -1, -1};
  static int[] dyb = {1, -1, -1, 1};
  static int[] dxr = {0, 1, 0, -1};
  static int[] dyr = {1, 0, -1, 0};

  static int N;
  static int[][] board = new int[10][10];
  static Queue<Pos> q = new LinkedList<>();
  static Dist[][][][] dist = new Dist[10][10][101][3];

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    N = in.nextInt();

    for (int i = 0; i < 10; i++) {
      for (int j = 0; j < 10; j++) {
        for (int k = 0; k < 101; k++) {
          for (int l = 0; l < 3; l++) {
            dist[i][j][k][l] = new Dist(-1, -1);
          }
        }
      }
    }

    for (int y = 0; y < N; y++) {
      for (int x = 0; x < N; x++) {
        board[y][x] = in.nextInt();

        if (board[y][x] == 1) {
          for (int p = 0; p < 3; p++) {
            q.add(new Pos(x, y, 1, p));
            dist[y][x][1][p] = new Dist(0, 0);
          }
        }
      }
    }

    Dist result = bfs();

    out.printf("%d %d", result.d, result.c);

    out.flush();
  }

  static Dist bfs() {
    Dist result = new Dist(987654321, 987654321);

    while (!q.isEmpty()) {
      Pos cur = q.remove();

      if (cur.n == N * N) {
        result = Collections.min(Arrays.asList(result, dist[cur.y][cur.x][cur.n][cur.p]));
      }

      changePiece(cur);

      if (cur.p == 0) {
        moveKnight(cur);
      } else if (cur.p == 1) {
        moveBishop(cur);
      } else if (cur.p == 2) {
        moveRook(cur);
      }
    }

    return result;
  }

  static void changePiece(Pos cur) {
    Dist dc = dist[cur.y][cur.x][cur.n][cur.p];
    for (int p = 0; p < 3; p++) {
      Dist dn = dist[cur.y][cur.x][cur.n][p];
      Dist d = new Dist(dc.d + 1, dc.c + 1);

      if (dn.d != -1 && dn.compareTo(d) <= 0) {
        continue;
      }

      q.add(new Pos(cur.x, cur.y, cur.n, p));
      dist[cur.y][cur.x][cur.n][p] = d;
    }
  }

  static void moveKnight(Pos cur) {
    for (int k = 0; k < 8; k++) {
      int xn = cur.x + dxk[k];
      int yn = cur.y + dyk[k];

      if (!canMove(xn, yn)) {
        continue;
      }

      move(cur, xn, yn);
    }
  }

  static void moveBishop(Pos cur) {
    for (int k = 0; k < 4; k++) {
      for (int d = 1; ; d++) {
        int xn = cur.x + dxb[k] * d;
        int yn = cur.y + dyb[k] * d;

        if (!canMove(xn, yn)) {
          break;
        }

        move(cur, xn, yn);
      }
    }
  }

  static void moveRook(Pos cur) {
    for (int k = 0; k < 4; k++) {
      for (int d = 1; ; d++) {
        int xn = cur.x + dxr[k] * d;
        int yn = cur.y + dyr[k] * d;

        if (!canMove(xn, yn)) {
          break;
        }

        move(cur, xn, yn);
      }
    }
  }

  static boolean canMove(int x, int y) {
    return 0 <= x && x < N && 0 <= y && y < N;
  }

  static void move(Pos cur, int xn, int yn) {
    int nn = cur.n;
    if (board[yn][xn] == cur.n + 1) {
      nn += 1;
    }

    Dist dc = dist[cur.y][cur.x][cur.n][cur.p];
    Dist dn = dist[yn][xn][nn][cur.p];
    Dist d = new Dist(dc.d + 1, dc.c);

    if (dn.d != -1 && dn.compareTo(d) <= 0) {
      return;
    }

    q.add(new Pos(xn, yn, nn, cur.p));
    dist[yn][xn][nn][cur.p] = d;
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