import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Main {

  static final int[] dx = {0, 1, 0, -1};
  static final int[] dy = {-1, 0, 1, 0};

  static int N, M;
  static int[][] grid = new int[50][50];
  static int[] bid = new int[2500];
  static int result = 0;

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    N = in.nextInt();
    M = in.nextInt();

    for (int y = 0; y < N; y++) {
      for (int x = 0; x < N; x++) {
        grid[y][x] = in.nextInt();
      }
    }

    while (M-- > 0) {
      int d = in.nextInt();
      int s = in.nextInt();

      if (d == 1) {
        d = 0;
      }
      if (d == 4) {
        d = 1;
      }

      blizzard(d, s);
      pop();
      divide();
    }

    out.println(result);

    out.flush();
  }

  static void blizzard(int d, int s) {
    int x = N / 2;
    int y = N / 2;
    for (int i = 0; i < s; i++) {
      x += dx[d];
      y += dy[d];

      grid[y][x] = 0;
    }

    makeArray();
    move();
  }

  static void makeArray() {
    int x = N / 2 - 1;
    int y = N / 2;
    int dir = 3;

    int maxDist = 1;
    int curDist = 0;

    for (int i = 0; i < N * N - 1; i++) {
      bid[i] = grid[y][x];
      curDist += 1;

      if (curDist == maxDist) {
        dir = (dir + 3) % 4;
        curDist = 0;
        if (dir == 1 || dir == 3) {
          maxDist += 1;
        }
      }

      x += dx[dir];
      y += dy[dir];
    }
  }

  static void move() {
    int[] result = new int[N * N];

    int index = 0;
    for (int i = 0; i < N * N - 1; i++) {
      if (bid[i] == 0) {
        continue;
      }

      result[index++] = bid[i];
    }

    bid = result;
  }

  static void pop() {
    boolean popped = false;

    int cur = 0;
    int count = 0;
    for (int i = 0; i < N * N - 1; i++) {
      if (cur == bid[i]) {
        count += 1;
      } else {
        if (count >= 4) {
          popped = true;
          for (int j = 1; j <= count; j++) {
            bid[i - j] = 0;
          }

          result += cur * count;
        }

        cur = bid[i];
        count = 1;
      }
    }

    move();
    if (popped) {
      pop();
    }
  }

  static void divide() {
    int[] result = new int[N * N + 1];

    int cur = bid[0];
    int count = 1;
    int index = 0;
    for (int i = 1; i < N * N; i++) {
      if (cur == bid[i]) {
        count += 1;
      } else {
        result[index++] = count;
        result[index++] = cur;

        cur = bid[i];
        count = 1;
      }

      if (index > N * N) {
        break;
      }
    }

    bid = result;
    makeGrid();
  }

  static void makeGrid() {
    int[][] result = new int[N][N];

    int x = N / 2 - 1;
    int y = N / 2;
    int dir = 3;

    int maxDist = 1;
    int curDist = 0;

    for (int i = 0; i < N * N - 1; i++) {
      result[y][x] = bid[i];
      curDist += 1;

      if (curDist == maxDist) {
        dir = (dir + 3) % 4;
        curDist = 0;
        if (dir == 1 || dir == 3) {
          maxDist += 1;
        }
      }

      x += dx[dir];
      y += dy[dir];
    }

    grid = result;
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