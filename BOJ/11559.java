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
  static final int[] dy = {1, 0, -1, 0};

  static char[][] field = new char[12][6];

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    for (int y = 0; y < 12; y++) {
      field[y] = in.next().toCharArray();
    }

    int result = 0;
    while (puyo()) {
      result += 1;
    }

    out.println(result);

    out.flush();
  }

  static boolean puyo() {
    boolean popped = false;
    for (int y = 0; y < 12; y++) {
      for (int x = 0; x < 6; x++) {
        if (field[y][x] != '.') {
          if (pop(x, y)) {
            popped = true;
          }
        }
      }
    }

    if (popped) {
      gravity();
    }

    return popped;
  }

  static boolean pop(int x, int y) {
    Queue<Pos> q = new LinkedList<>();
    boolean[][] visited = new boolean[12][6];

    q.add(new Pos(x, y));
    visited[y][x] = true;

    int result = 1;
    while (!q.isEmpty()) {
      Pos cur = q.remove();

      for (int k = 0; k < 4; k++) {
        int xn = cur.x + dx[k];
        int yn = cur.y + dy[k];

        if (xn < 0 || yn < 0 || xn > 5 || yn > 11) {
          continue;
        }

        if (visited[yn][xn] || field[yn][xn] != field[y][x]) {
          continue;
        }

        q.add(new Pos(xn, yn));
        visited[yn][xn] = true;
        result += 1;
      }
    }

    if (result <= 3) {
      return false;
    }

    for (int r = 0; r < 12; r++) {
      for (int c = 0; c < 6; c++) {
        if (visited[r][c]) {
          field[r][c] = '.';
        }
      }
    }

    return true;
  }

  static void gravity() {
    for (int y = 11; y >= 0; y--) {
      for (int x = 0; x < 6; x++) {
        if (field[y][x] != '.') {
          drop(x, y);
        }
      }
    }
  }

  static void drop(int x, int y) {
    while (y < 11 && field[y + 1][x] == '.') {
      swap(x, y, x, y + 1);
      y += 1;
    }
  }

  static void swap(int xi, int yi, int xj, int yj) {
    char temp = field[yi][xi];
    field[yi][xi] = field[yj][xj];
    field[yj][xj] = temp;
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
}