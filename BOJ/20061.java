import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Main {

  static final int[] dx = {0, 0, 1, 0};
  static final int[] dy = {0, 0, 0, 1};

  static int[][] g = new int[6][4];
  static int[][] b = new int[6][4];
  static int result = 0;

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    int N = in.nextInt();
    while (N-- > 0) {
      int t = in.nextInt();
      int y = in.nextInt();
      int x = in.nextInt();

      if (t == 1) {
        simulate(g, 1, x);
        simulate(b, 1, y);
      } else if (t == 2) {
        simulate(g, 2, x);
        simulate(b, 3, y);
      } else if (t == 3) {
        simulate(g, 3, x);
        simulate(b, 2, y);
      }
    }

    int count = 0;
    for (int y = 0; y < 6; y++) {
      for (int x = 0; x < 4; x++) {
        count += g[y][x];
        count += b[y][x];
      }
    }

    out.println(result);
    out.println(count);

    out.flush();
  }

  static void simulate(int[][] board, int t, int x) {
    addBlock(board, t, x);
    eraseBlock(board);
    shiftBlock(board);
  }

  static void addBlock(int[][] board, int t, int x) {
    int y = 0;

    while (y + 1 <= 5 && canMove(board, t, x, y + 1)) {
      y += 1;
    }

    board[y][x] = board[y + dy[t]][x + dx[t]] = 1;
  }

  static boolean canMove(int[][] board, int t, int x, int y) {
    return y + dy[t] <= 5 && board[y][x] == 0 && board[y + dy[t]][x + dx[t]] == 0;
  }

  static void eraseBlock(int[][] board) {
    for (int y = 5; y >= 2; ) {
      if (isFull(board, y)) {
        result += 1;
        
        down(board, y);
      } else {
        y -= 1;
      }
    }
  }

  static boolean isFull(int[][] board, int y) {
    for (int x = 0; x < 4; x++) {
      if (board[y][x] == 0) {
        return false;
      }
    }
    return true;
  }

  static void down(int[][] board, int y) {
    for (int r = y; r > 0; r--) {
      board[r] = board[r - 1];
    }
    board[0] = new int[4];
  }

  static void shiftBlock(int[][] board) {
    for (int x = 0; x < 4; x++) {
      if (board[1][x] != 0) {
        down(board, 5);

        shiftBlock(board);
        return;
      }
    }
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