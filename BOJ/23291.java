import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Main {

  static final int[] dx = {0, 1, 0, -1};
  static final int[] dy = {-1, 0, 1, 0};

  static int N, K;
  static int[] fish = new int[100];
  static int[][] fishbowl = new int[100][100];

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    N = in.nextInt();
    K = in.nextInt();

    fish = new int[N];
    for (int i = 0; i < N; i++) {
      fish[i] = in.nextInt();
    }

    int result = 1;
    while (true) {
      addFish();

      fishbowl = new int[N][N];
      first();
      divide();
      putFishbowl();

      fishbowl = new int[N][N];
      second();
      divide();
      putFishbowl();

      if (check()) {
        break;
      }

      result += 1;
    }

    out.println(result);

    out.flush();
  }

  static void addFish() {
    int min = 987654321;
    for (int i = 0; i < N; i++) {
      min = Math.min(min, fish[i]);
    }

    for (int i = 0; i < N; i++) {
      if (fish[i] == min) {
        fish[i] += 1;
      }
    }
  }

  static void first() {
    int index = 0;

    fishbowl[0][0] = fish[index++];

    while (true) {
      rotate();
      pushLeft();

      int[] bowlSize = getBowlSize();
      for (int x = 0; x < bowlSize[0]; x++) {
        fishbowl[bowlSize[1]][x] = fish[index++];
      }

      bowlSize = getBowlSize();
      if (bowlSize[1] > N - index) {
        int x = bowlSize[0];
        while (index < N) {
          fishbowl[bowlSize[1] - 1][x++] = fish[index++];
        }
        break;
      }
    }
  }

  static void second() {
    int n = N / 2;
    int index = 0;

    for (int i = 0; i < n; i++) {
      fishbowl[0][i] = fish[index++];
    }

    for (int i = 0; i < 2; i++) {
      rotate();
      pushLeft();
    }

    for (int x = 0; x < n; x++) {
      fishbowl[1][x] = fish[index++];
    }

    n /= 2;

    int[][] left = new int[N][N];
    for (int y = 0; y < 2; y++) {
      for (int x = 0; x < n; x++) {
        left[y][x] = fishbowl[y][x];
      }
    }

    int[][] right = new int[2][n];
    for (int y = 0; y < 2; y++) {
      for (int x = n; x < 2 * n; x++) {
        right[y][x - n] = fishbowl[y][x];
      }
    }

    fishbowl = left;
    for (int i = 0; i < 2; i++) {
      rotate();
      pushLeft();
    }

    for (int y = 2; y < 4; y++) {
      for (int x = 0; x < n; x++) {
        fishbowl[y][x] = right[y - 2][x];
      }
    }
  }

  static void divide() {
    int[][] result = new int[N][N];

    for (int y = 0; y < N; y++) {
      for (int x = 0; x < N; x++) {
        if (fishbowl[y][x] == 0) {
          continue;
        }

        int f = fishbowl[y][x];
        for (int k = 0; k < 4; k++) {
          int xn = x + dx[k];
          int yn = y + dy[k];

          if (xn < 0 || yn < 0 || xn > N - 1 || yn > N - 1) {
            continue;
          }

          if (fishbowl[yn][xn] == 0) {
            continue;
          }

          if (fishbowl[y][x] > fishbowl[yn][xn]) {
            int d = fishbowl[y][x] - fishbowl[yn][xn];

            f -= d / 5;
            result[yn][xn] += d / 5;
          }
        }

        result[y][x] += f;
      }
    }

    fishbowl = result;
  }

  static void putFishbowl() {
    int index = 0;
    for (int x = 0; x < N; x++) {
      for (int y = N - 1; y >= 0; y--) {
        if (fishbowl[y][x] == 0) {
          continue;
        }

        fish[index++] = fishbowl[y][x];
      }
    }
  }

  static boolean check() {
    int max = -1;
    int min = 987654321;

    for (int i = 0; i < N; i++) {
      max = Math.max(max, fish[i]);
      min = Math.min(min, fish[i]);
    }

    return (max - min) <= K;
  }

  static void rotate() {
    int[][] result = new int[N][N];

    for (int y = 0; y < N; y++) {
      for (int x = 0; x < N; x++) {
        result[y][x] = fishbowl[N - 1 - x][y];
      }
    }

    fishbowl = result;
  }

  static void pushLeft() {
    int[][] result = new int[N][N];

    for (int y = 0; y < N; y++) {
      int index = 0;
      for (int x = 0; x < N; x++) {
        if (fishbowl[y][x] == 0) {
          continue;
        }
        result[y][index++] = fishbowl[y][x];
      }
    }

    fishbowl = result;
  }

  static int[] getBowlSize() {
    int[] result = new int[2];

    int x = 0;
    int y = 0;
    while (x < N && fishbowl[0][x] != 0) {
      x += 1;
    }
    while (y < N && fishbowl[y][0] != 0) {
      y += 1;
    }

    result[0] = x;
    result[1] = y;

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