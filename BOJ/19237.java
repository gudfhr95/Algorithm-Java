import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

class Pos {

  int x, y, d;

  public Pos(int x, int y, int d) {
    this.x = x;
    this.y = y;
    this.d = d;
  }
}

class Smell {

  int n, t;

  public Smell(int n, int t) {
    this.n = n;
    this.t = t;
  }
}

public class Main {

  static final int[] dx = {0, 0, 0, -1, 1};
  static final int[] dy = {0, -1, 1, 0, 0};

  static int N, M, k;
  static Pos[] sharks = new Pos[401];
  static int[][] board = new int[20][20];
  static Smell[][] smells = new Smell[20][20];
  static int[][][] dir = new int[400][5][4];
  static int time = 0;

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    N = in.nextInt();
    M = in.nextInt();
    k = in.nextInt();

    for (int y = 0; y < N; y++) {
      for (int x = 0; x < N; x++) {
        board[y][x] = in.nextInt();
        smells[y][x] = null;

        if (board[y][x] != 0) {
          sharks[board[y][x]] = new Pos(x, y, 0);
          smells[y][x] = new Smell(board[y][x], 0);
        }
      }
    }

    for (int i = 1; i <= M; i++) {
      sharks[i].d = in.nextInt();
    }

    for (int i = 1; i <= M; i++) {
      for (int y = 1; y <= 4; y++) {
        for (int x = 0; x < 4; x++) {
          dir[i][y][x] = in.nextInt();
        }
      }
    }

    out.println(simulate());

    out.flush();
  }

  static int simulate() {
    while (++time <= 1000) {
      for (int i = 1; i <= M; i++) {
        if (sharks[i] == null) {
          continue;
        }

        moveShark(i);
      }

      int remaining = 0;
      for (int i = 1; i <= M; i++) {
        Pos shark = sharks[i];
        if (sharks[i] != null) {
          smells[shark.y][shark.x] = new Smell(i, time);

          remaining += 1;
        }
      }

      if (remaining == 1) {
        return time;
      }
    }

    return -1;
  }

  static void moveShark(int i) {
    Pos shark = sharks[i];

    for (int d : dir[i][shark.d]) {
      int xn = shark.x + dx[d];
      int yn = shark.y + dy[d];

      if (xn < 0 || yn < 0 || xn > N - 1 || yn > N - 1) {
        continue;
      }

      if (smells[yn][xn] != null && time - smells[yn][xn].t <= k) {
        continue;
      }

      move(i, xn, yn, d);

      return;
    }

    for (int d : dir[i][shark.d]) {
      int xn = shark.x + dx[d];
      int yn = shark.y + dy[d];

      if (xn < 0 || yn < 0 || xn > N - 1 || yn > N - 1) {
        continue;
      }

      if (smells[yn][xn].n != i) {
        continue;
      }

      move(i, xn, yn, d);

      return;
    }
  }

  static void move(int i, int x, int y, int d) {
    Pos shark = sharks[i];

    board[shark.y][shark.x] = 0;

    if (board[y][x] != 0 && board[y][x] < i) {
      sharks[i] = null;
      return;
    }

    board[y][x] = i;
    shark.x = x;
    shark.y = y;
    shark.d = d;
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