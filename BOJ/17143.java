import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

class Shark {

  int r, c, s, d, z;

  public Shark(int r, int c, int s, int d, int z) {
    this.r = r;
    this.c = c;
    this.s = s;
    this.d = d;
    this.z = z;
  }
}

public class Main {

  static final int[] dx = {0, 0, 0, 1, -1};
  static final int[] dy = {0, -1, 1, 0, 0};

  static int R, C;
  static Shark[][] board = new Shark[101][101];

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    R = in.nextInt();
    C = in.nextInt();

    int M = in.nextInt();
    while (M-- > 0) {
      int r = in.nextInt();
      int c = in.nextInt();
      int s = in.nextInt();
      int d = in.nextInt();
      int z = in.nextInt();

      board[r][c] = new Shark(r, c, s, d, z);
    }

    int result = 0;
    for (int c = 1; c <= C; c++) {
      result += catchShark(c);
      move();
    }

    out.println(result);

    out.flush();
  }

  static int catchShark(int c) {
    for (int r = 1; r <= R; r++) {
      if (board[r][c] != null) {
        int result = board[r][c].z;
        board[r][c] = null;
        return result;
      }
    }

    return 0;
  }

  static void move() {
    Shark[][] result = new Shark[101][101];

    for (int r = 1; r <= R; r++) {
      for (int c = 1; c <= C; c++) {
        if (board[r][c] == null) {
          continue;
        }

        Shark shark = moveShark(board[r][c]);

        if (result[shark.r][shark.c] == null || result[shark.r][shark.c].z < shark.z) {
          result[shark.r][shark.c] = shark;
        }
      }
    }

    board = result;
  }

  static Shark moveShark(Shark shark) {
    for (int i = 0; i < shark.s; i++) {
      shark.c += dx[shark.d];
      shark.r += dy[shark.d];

      if (shark.c > C) {
        shark.c -= 2;
        shark.d = 4;
      } else if (shark.c < 1) {
        shark.c += 2;
        shark.d = 3;
      } else if (shark.r > R) {
        shark.r -= 2;
        shark.d = 1;
      } else if (shark.r < 1) {
        shark.r += 2;
        shark.d = 2;
      }
    }

    return shark;
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