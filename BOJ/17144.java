import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Main {

  static final int[] dx = {0, 1, 0, -1};
  static final int[] dy = {1, 0, -1, 0};

  static int R, C, x1, y1, x2, y2;
  static int[][] room = new int[50][50];

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    R = in.nextInt();
    C = in.nextInt();
    int T = in.nextInt();

    x1 = x2 = y1 = y2 = -1;
    for (int y = 0; y < R; y++) {
      for (int x = 0; x < C; x++) {
        room[y][x] = in.nextInt();

        if (room[y][x] == -1) {
          if (x1 == -1 && y1 == -1) {
            x1 = x;
            y1 = y;
          } else {
            x2 = x;
            y2 = y;
          }
        }
      }
    }

    while (T-- > 0) {
      expand();
      cycleUp();
      cycleDown();
    }

    int result = 0;
    for (int y = 0; y < R; y++) {
      for (int x = 0; x < C; x++) {
        if (room[y][x] <= 0) {
          continue;
        }

        result += room[y][x];
      }
    }

    out.println(result);

    out.flush();
  }

  static void expand() {
    int[][] result = new int[50][50];

    for (int y = 0; y < R; y++) {
      for (int x = 0; x < C; x++) {
        if (room[y][x] == -1) {
          result[y][x] = room[y][x];
          continue;
        }

        int amount = room[y][x] / 5;
        for (int k = 0; k < 4; k++) {
          int xn = x + dx[k];
          int yn = y + dy[k];

          if (xn < 0 || yn < 0 || xn > C - 1 || yn > R - 1) {
            continue;
          }

          if (room[yn][xn] == -1) {
            continue;
          }

          result[yn][xn] += amount;
          room[y][x] -= amount;
        }

        result[y][x] += room[y][x];
      }
    }

    room = result;
  }

  static void cycleUp() {
    for (int y = y1 - 1; y > 0; y--) {
      room[y][0] = room[y - 1][0];
    }
    for (int x = 0; x < C - 1; x++) {
      room[0][x] = room[0][x + 1];
    }
    for (int y = 0; y < y1; y++) {
      room[y][C - 1] = room[y + 1][C - 1];
    }
    for (int x = C - 1; x >= 2; x--) {
      room[y1][x] = room[y1][x - 1];
    }
    room[y1][1] = 0;
  }

  static void cycleDown() {
    for (int y = y2 + 1; y < R - 1; y++) {
      room[y][0] = room[y + 1][0];
    }
    for (int x = 0; x < C - 1; x++) {
      room[R - 1][x] = room[R - 1][x + 1];
    }
    for (int y = R - 1; y > y2; y--) {
      room[y][C - 1] = room[y - 1][C - 1];
    }
    for (int x = C - 1; x >= 2; x--) {
      room[y2][x] = room[y2][x - 1];
    }
    room[y2][1] = 0;
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