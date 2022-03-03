import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Main {

  static final int[] dx = {0, 1, 0, -1};
  static final int[] dy = {-1, 0, 1, 0};
  
  static final float[][] sandRatio = {
      {0, 0, 0.05f, 0, 0},
      {0, 0.1f, 0, 0.1f, 0},
      {0.02f, 0.07f, 0, 0.07f, 0.02f},
      {0, 0.01f, 0, 0.01f, 0},
      {0, 0, 0, 0, 0}
  };

  static int N;
  static int[][] grid = new int[499][499];
  static int result = 0;

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    N = in.nextInt();
    for (int y = 0; y < N; y++) {
      for (int x = 0; x < N; x++) {
        grid[y][x] = in.nextInt();
      }
    }

    tornado();

    out.println(result);

    out.flush();
  }

  static void tornado() {
    int x = N / 2;
    int y = N / 2;
    int dist = 1;
    int cur = 0;
    int dir = 3;

    while (true) {
      if (x == 0 && y == 0) {
        return;
      }

      x += dx[dir];
      y += dy[dir];
      wind(x, y, dir);

      cur += 1;
      if (cur == dist) {
        cur = 0;
        dir = (dir + 3) % 4;

        if (dir == 1 || dir == 3) {
          dist += 1;
        }
      }
    }
  }

  static void wind(int x, int y, int d) {
    int[][] sands = getSandMatrix(x, y, d);

    for (int r = 0; r < 5; r++) {
      for (int c = 0; c < 5; c++) {
        int xn = x + c - 2;
        int yn = y + r - 2;

        if (xn < 0 || yn < 0 || xn > N - 1 || yn > N - 1) {
          result += sands[r][c];
        } else {
          grid[yn][xn] += sands[r][c];
        }
      }
    }

    grid[y][x] = 0;
  }

  static int[][] getSandMatrix(int x, int y, int d) {
    float[][] matrix = getSandRatioMatrix(d);

    int[][] sands = new int[5][5];
    int moved = 0;
    for (int r = 0; r < 5; r++) {
      for (int c = 0; c < 5; c++) {
        int sand = (int) (matrix[r][c] * grid[y][x]);

        sands[r][c] = sand;
        moved += sand;
      }
    }

    sands[2 + dy[d]][2 + dx[d]] = grid[y][x] - moved;

    return sands;
  }

  static float[][] getSandRatioMatrix(int d) {
    float[][] result = new float[5][5];

    for (int y = 0; y < 5; y++) {
      for (int x = 0; x < 5; x++) {
        result[y][x] = sandRatio[y][x];
      }
    }

    for (int i = 0; i < d; i++) {
      result = rotate(result);
    }

    return result;
  }

  static float[][] rotate(float[][] matrix) {
    float[][] result = new float[5][5];
    for (int y = 0; y < 5; y++) {
      for (int x = 0; x < 5; x++) {
        result[x][4 - y] = matrix[y][x];
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
