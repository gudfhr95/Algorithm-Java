import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
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

  static final int BLACK = -1;
  static final int RAINBOW = 0;
  static final int EMPTY = -2;

  static final int[] dx = {0, 1, 0, -1};
  static final int[] dy = {-1, 0, 1, 0};

  static int N, M;
  static int[][] blocks = new int[20][20];

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    N = in.nextInt();
    M = in.nextInt();

    for (int y = 0; y < N; y++) {
      for (int x = 0; x < N; x++) {
        blocks[y][x] = in.nextInt();
      }
    }

    int result = 0;
    while (true) {
      Pos p = getMaxPos();

      if (p.x == -1 && p.y == -1) {
        break;
      }

      result += eraseBlock(p);
      gravity();
      rotate();
      gravity();
    }

    out.println(result);

    out.flush();
  }

  static Pos getMaxPos() {
    int[] maxCount = {-1, -1};
    int maxX = -1;
    int maxY = -1;

    boolean[][] visited = new boolean[N][N];
    for (int y = 0; y < N; y++) {
      for (int x = 0; x < N; x++) {
        if (blocks[y][x] > 0 && !visited[y][x]) {
          int[] count = getScore(x, y, visited);

          if (count[0] == -1 && count[1] == -1) {
            continue;
          }

          if (count[0] < maxCount[0]) {
            continue;
          }

          if (count[0] == maxCount[0] && count[1] < maxCount[1]) {
            continue;
          }

          maxCount = count;
          maxX = x;
          maxY = y;
        }
      }
    }

    return new Pos(maxX, maxY);
  }

  static int[] getScore(int x, int y, boolean[][] visited) {
    Queue<Pos> q = new LinkedList<>();

    q.add(new Pos(x, y));
    visited[y][x] = true;

    int color = blocks[y][x];
    int result = 1;
    int rainbow = 0;

    while (!q.isEmpty()) {
      Pos cur = q.remove();

      for (int k = 0; k < 4; k++) {
        int xn = cur.x + dx[k];
        int yn = cur.y + dy[k];

        if (xn < 0 || yn < 0 || xn > N - 1 || yn > N - 1) {
          continue;
        }

        if (visited[yn][xn]) {
          continue;
        }

        if (blocks[yn][xn] != color && blocks[yn][xn] != RAINBOW) {
          continue;
        }

        q.add(new Pos(xn, yn));
        visited[yn][xn] = true;
        result += 1;
        if (blocks[yn][xn] == RAINBOW) {
          rainbow += 1;
        }
      }
    }

    for (int r = 0; r < N; r++) {
      for (int c = 0; c < N; c++) {
        if (blocks[r][c] == RAINBOW) {
          visited[r][c] = false;
        }
      }
    }

    if (result == 1) {
      return new int[]{-1, -1};
    } else {
      return new int[]{result, rainbow};
    }
  }

  static int eraseBlock(Pos p) {
    Queue<Pos> q = new LinkedList<>();
    boolean[][] visited = new boolean[N][N];

    q.add(p);
    visited[p.y][p.x] = true;

    int color = blocks[p.y][p.x];
    int result = 1;

    while (!q.isEmpty()) {
      Pos cur = q.remove();
      blocks[cur.y][cur.x] = EMPTY;

      for (int k = 0; k < 4; k++) {
        int xn = cur.x + dx[k];
        int yn = cur.y + dy[k];

        if (xn < 0 || yn < 0 || xn > N - 1 || yn > N - 1) {
          continue;
        }

        if (visited[yn][xn]) {
          continue;
        }

        if (blocks[yn][xn] != color && blocks[yn][xn] != RAINBOW) {
          continue;
        }

        q.add(new Pos(xn, yn));
        visited[yn][xn] = true;
        result += 1;
      }
    }

    return result * result;
  }

  static void gravity() {
    for (int y = N - 1; y >= 0; y--) {
      for (int x = N - 1; x >= 0; x--) {
        if (blocks[y][x] >= 0) {
          down(x, y);
        }
      }
    }
  }

  static void down(int x, int y) {
    int yn = y + 1;
    while (yn < N) {
      if (blocks[yn][x] == EMPTY) {
        blocks[yn][x] = blocks[yn - 1][x];
        blocks[yn - 1][x] = EMPTY;
        yn += 1;
      } else {
        break;
      }
    }
  }

  static void rotate() {
    int[][] result = new int[N][N];

    for (int y = 0; y < N; y++) {
      for (int x = 0; x < N; x++) {
        result[y][x] = blocks[x][N - 1 - y];
      }
    }

    blocks = result;
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