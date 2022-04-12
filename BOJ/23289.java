import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;


class Pos {

  int x, y, d;

  Pos(int x, int y, int d) {
    this.x = x;
    this.y = y;
    this.d = d;
  }
}

public class Main {

  static final int[] dx = {0, 0, 1, 0, -1};
  static final int[] dy = {0, -1, 0, 1, 0};
  static final int[] dir = {0, 2, 4, 1, 3};

  static int R, C, K;
  static int[][] temp = new int[21][21];
  static boolean[][] visited = new boolean[21][21];
  static ArrayList<Pos> heaters = new ArrayList<>();
  static ArrayList<Pos> targets = new ArrayList<>();
  static boolean[][][] walls = new boolean[21][21][5];

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    R = in.nextInt();
    C = in.nextInt();
    K = in.nextInt();

    for (int y = 1; y <= R; y++) {
      for (int x = 1; x <= C; x++) {
        int v = in.nextInt();

        if (v == 0) {
          continue;
        }

        if (v == 5) {
          targets.add(new Pos(x, y, 0));
        } else {
          heaters.add(new Pos(x, y, dir[v]));
        }
      }
    }

    int W = in.nextInt();
    while (W-- > 0) {
      int y = in.nextInt();
      int x = in.nextInt();
      int t = in.nextInt();

      if (t == 0) {
        walls[y][x][1] = walls[y - 1][x][3] = true;
      } else {
        walls[y][x][2] = walls[y][x + 1][4] = true;
      }
    }

    out.println(simulate());

    out.flush();
  }

  static int simulate() {
    int c = 1;
    for (; c <= 100; c++) {
      for (Pos p : heaters) {
        if (walls[p.y][p.x][p.d]) {
          continue;
        }

        for (boolean[] a1 : visited) {
          Arrays.fill(a1, false);
        }

        int xn = p.x + dx[p.d];
        int yn = p.y + dy[p.d];
        if (isValidPos(xn, yn)) {
          heat(new Pos(xn, yn, p.d), 5);
        }

      }

      balance();
      coolDown();

      if (check()) {
        break;
      }
    }

    return c;
  }

  static void heat(Pos p, int t) {
    if (t == 0) {
      return;
    }

    temp[p.y][p.x] += t;
    visited[p.y][p.x] = true;

    int d1 = p.d - 1;
    int d2 = p.d + 1;
    if (d1 == 0) {
      d1 = 4;
    }
    if (d2 == 5) {
      d2 = 1;
    }

    int xn = p.x + dx[d1];
    int yn = p.y + dy[d1];
    if (isValidPos(xn, yn)) {
      if (!walls[p.y][p.x][d1] && !walls[yn][xn][p.d]) {
        xn += dx[p.d];
        yn += dy[p.d];

        if (isValidPos(xn, yn) && !visited[yn][xn]) {
          heat(new Pos(xn, yn, p.d), t - 1);
        }
      }
    }

    xn = p.x + dx[p.d];
    yn = p.y + dy[p.d];
    if (isValidPos(xn, yn)) {
      if (!walls[p.y][p.x][p.d]) {
        if (!visited[yn][xn]) {
          heat(new Pos(xn, yn, p.d), t - 1);
        }
      }
    }

    xn = p.x + dx[d2];
    yn = p.y + dy[d2];
    if (isValidPos(xn, yn)) {
      if (!walls[p.y][p.x][d2] && !walls[yn][xn][p.d]) {
        xn += dx[p.d];
        yn += dy[p.d];

        if (isValidPos(xn, yn) && !visited[yn][xn]) {
          heat(new Pos(xn, yn, p.d), t - 1);
        }
      }
    }
  }

  static void balance() {
    int[][] result = new int[21][21];
    for (int y = 1; y <= R; y++) {
      for (int x = 1; x <= C; x++) {
        int t = temp[y][x];
        for (int k = 1; k < 5; k++) {
          if (walls[y][x][k]) {
            continue;
          }

          int xn = x + dx[k];
          int yn = y + dy[k];
          if (!isValidPos(xn, yn)) {
            continue;
          }

          if (temp[yn][xn] >= temp[y][x]) {
            continue;
          }

          int diff = temp[y][x] - temp[yn][xn];
          result[yn][xn] += diff / 4;
          t -= diff / 4;
        }
        result[y][x] += t;
      }
    }

    temp = result;
  }

  static void coolDown() {
    for (int y = 1; y <= R; y++) {
      temp[y][1] -= 1;
      temp[y][C] -= 1;

      temp[y][1] = Math.max(temp[y][1], 0);
      temp[y][C] = Math.max(temp[y][C], 0);
    }

    for (int x = 2; x < C; x++) {
      temp[1][x] -= 1;
      temp[R][x] -= 1;

      temp[1][x] = Math.max(temp[1][x], 0);
      temp[R][x] = Math.max(temp[R][x], 0);
    }
  }

  static boolean check() {
    for (Pos p : targets) {
      if (temp[p.y][p.x] < K) {
        return false;
      }
    }
    return true;
  }

  static boolean isValidPos(int x, int y) {
    return 1 <= x && x <= C && 1 <= y && y <= R;
  }
}

class InputReader {

  BufferedReader reader;
  StringTokenizer tokenizer;

  InputReader(InputStream stream) {
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