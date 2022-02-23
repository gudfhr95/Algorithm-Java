import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Main {

  static final int[] dx = {0, 0, 1, 0};
  static final int[] dy = {0, 0, 0, 1};

  static int number = 1;

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
        if (b[y][x] != 0) {
          count += 1;
        }
        if (g[y][x] != 0) {
          count += 1;
        }
      }
    }

    out.println(result);
    out.println(count);

    out.flush();
  }

  static void simulate(int[][] board, int t, int x) {
    addBlock(board, t, x);
    eraseRow(board);
    shiftRow(board);
  }

  static void addBlock(int[][] board, int t, int x) {
    int y = 0;
    while (y + 1 <= 5 && canMove(board, t, x, y + 1)) {
      y += 1;
    }

    board[y][x] = board[y + dy[t]][x + dx[t]] = number++;
  }

  static void eraseRow(int[][] board) {
    boolean erased = false;
    for (int y = 5; y >= 2; y--) {
      if (isFull(board, y)) {
        board[y] = new int[4];

        erased = true;
        result += 1;
      }
    }

    if (erased) {
      for (int r = 5; r >= 0; r--) {
        down(board, r);
      }
      eraseRow(board);
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
    for (int x = 0; x < 4; x++) {
      if (board[y][x] == 0) {
        continue;
      }

      int t = getType(board, x, y);

      int yn = t == 3 ? y - 1 : y;
      while (yn + 1 <= 5 && canMove(board, t, x, yn + 1)) {
        int temp = board[yn][x];

        board[yn][x] = board[yn + dy[t]][x + dx[t]] = 0;
        yn += 1;
        board[yn][x] = board[yn + dy[t]][x + dx[t]] = temp;
      }

      if (t == 2) {
        x += 1;
      }
    }
  }

  static int getType(int[][] board, int x, int y) {
    if (x <= 2 && board[y][x] == board[y][x + 1]) {
      return 2;
    } else if (y < 5 && board[y][x] == board[y - 1][x]) {
      return 3;
    } else {
      return 1;
    }
  }

  static boolean canMove(int[][] board, int t, int x, int y) {
    if (t == 1) {
      return board[y][x] == 0;
    } else if (t == 2) {
      return board[y][x] == 0 && board[y][x + 1] == 0;
    } else if (t == 3) {
      return y + 1 <= 5 && board[y + 1][x] == 0;
    }

    return false;
  }

  static void shiftRow(int[][] board) {
    for (int x = 0; x < 4; x++) {
      if (board[1][x] != 0) {
        for (int r = 5; r > 0; r--) {
          board[r] = board[r - 1];
        }
        board[0] = new int[4];

        shiftRow(board);

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