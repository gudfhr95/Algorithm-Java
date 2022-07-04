import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Main {

  static final int[][] dx = {
      {1, 0},
      {1, 1},
      {0, 1},
      {0, -1}
  };
  static final int[][] dy = {
      {0, 1},
      {0, 1},
      {1, 1},
      {1, 1}
  };

  static int C, H, W;
  static char[][] board = new char[20][20];

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    C = in.nextInt();

    while (C-- > 0) {
      H = in.nextInt();
      W = in.nextInt();

      for (int y = 0; y < H; y++) {
        board[y] = in.next().toCharArray();
      }

      out.println(cover());
    }

    out.flush();
  }

  static int cover() {
    int[] pos = findPos();

    if (pos[0] == -1 && pos[1] == -1) {
      return 1;
    }

    int result = 0;
    for (int k = 0; k < 4; k++) {
      int x1 = pos[0] + dx[k][0];
      int y1 = pos[1] + dy[k][0];
      int x2 = pos[0] + dx[k][1];
      int y2 = pos[1] + dy[k][1];

      if (x1 < 0 || y1 < 0 || x1 > W - 1 || y1 > H - 1) {
        continue;
      }

      if (x2 < 0 || y2 < 0 || x2 > W - 1 || y2 > H - 1) {
        continue;
      }

      if (board[pos[1]][pos[0]] != '#' && board[y1][x1] != '#' && board[y2][x2] != '#') {
        board[pos[1]][pos[0]] = board[y1][x1] = board[y2][x2] = '#';
        result += cover();
        board[pos[1]][pos[0]] = board[y1][x1] = board[y2][x2] = '.';
      }
    }

    return result;
  }

  static int[] findPos() {
    int[] result = new int[2];
    result[0] = result[1] = -1;

    for (int y = 0; y < H; y++) {
      for (int x = 0; x < W; x++) {
        if (board[y][x] == '.') {
          result[0] = x;
          result[1] = y;

          return result;
        }
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