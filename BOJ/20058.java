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

  static final int[] dx = {0, 1, 0, -1};
  static final int[] dy = {-1, 0, 1, 0};

  static int N, n;
  static int[][] grid = new int[64][64];
  static boolean[][] visited = new boolean[64][64];

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    N = in.nextInt();
    int Q = in.nextInt();

    n = (int) Math.pow(2, N);
    for (int y = 0; y < n; y++) {
      for (int x = 0; x < n; x++) {
        grid[y][x] = in.nextInt();
      }
    }

    while (Q-- > 0) {
      fireStorm(in.nextInt());
    }

    int totalIce = 0;
    int maxArea = 0;
    for (int y = 0; y < n; y++) {
      for (int x = 0; x < n; x++) {
        totalIce += grid[y][x];

        if (grid[y][x] != 0 && !visited[y][x]) {
          maxArea = Math.max(maxArea, bfs(x, y));
        }
      }
    }

    out.println(totalIce);
    out.println(maxArea);

    out.flush();
  }

  static void fireStorm(int L) {
    int l = (int) Math.pow(2, L);
    for (int y = 0; y < n; y += l) {
      for (int x = 0; x < n; x += l) {
        rotate(x, y, l);
      }
    }

    int[][] result = new int[n][n];
    for (int y = 0; y < n; y++) {
      for (int x = 0; x < n; x++) {
        result[y][x] = grid[y][x];

        if (grid[y][x] != 0 && isMelt(x, y)) {
          result[y][x] -= 1;
        }
      }
    }

    grid = result;
  }

  static void rotate(int x, int y, int l) {
    int[][] result = new int[l][l];
    for (int r = 0; r < l; r++) {
      for (int c = 0; c < l; c++) {
        result[c][l - 1 - r] = grid[y + r][x + c];
      }
    }

    for (int r = 0; r < l; r++) {
      for (int c = 0; c < l; c++) {
        grid[y + r][x + c] = result[r][c];
      }
    }
  }

  static boolean isMelt(int x, int y) {
    int count = 0;
    for (int k = 0; k < 4; k++) {
      int xn = x + dx[k];
      int yn = y + dy[k];

      if (xn < 0 || yn < 0 || xn > n - 1 || yn > n - 1) {
        continue;
      }

      if (grid[yn][xn] != 0) {
        count += 1;
      }
    }

    return count <= 2;
  }

  static int bfs(int x, int y) {
    Queue<Pos> q = new LinkedList<>();

    q.add(new Pos(x, y));
    visited[y][x] = true;

    int result = 1;
    while (!q.isEmpty()) {
      Pos cur = q.remove();

      for (int k = 0; k < 4; k++) {
        int xn = cur.x + dx[k];
        int yn = cur.y + dy[k];

        if (xn < 0 || yn < 0 || xn > n - 1 || yn > n - 1) {
          continue;
        }

        if (grid[yn][xn] == 0 || visited[yn][xn]) {
          continue;
        }

        q.add(new Pos(xn, yn));
        visited[yn][xn] = true;
        result += 1;
      }
    }

    return result;
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