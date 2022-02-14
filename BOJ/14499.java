import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Main {

  static InputReader in = new InputReader(System.in);
  static PrintWriter out = new PrintWriter(System.out);

  static int[][] idx = {
      {},
      {1, 4, 6, 3},
      {1, 3, 6, 4},
      {1, 5, 6, 2},
      {1, 2, 6, 5}
  };

  static int N, M, x, y;
  static int[] dice = new int[7];
  static int[][] map = new int[20][20];

  public static void main(String[] args) {
    N = in.nextInt();
    M = in.nextInt();
    y = in.nextInt();
    x = in.nextInt();
    int K = in.nextInt();

    for (int r = 0; r < N; r++) {
      for (int c = 0; c < M; c++) {
        map[r][c] = in.nextInt();
      }
    }

    for (int k = 0; k < K; k++) {
      move(in.nextInt());
    }

    out.flush();
  }

  static void move(int dir) {
    int xn = x;
    int yn = y;

    if (dir == 1) {
      xn += 1;
    } else if (dir == 2) {
      xn -= 1;
    } else if (dir == 3) {
      yn -= 1;
    } else {
      yn += 1;
    }

    if (xn < 0 || yn < 0 || xn > M - 1 || yn > N - 1) {
      return;
    }

    x = xn;
    y = yn;

    roll(dir);

    out.println(dice[1]);
  }

  static void roll(int dir) {
    int[] temp = new int[7];

    for (int i = 1; i <= 6; i++) {
      temp[i] = dice[i];
    }

    for (int i = 0; i < 4; i++) {
      temp[idx[dir][i]] = dice[idx[dir][(i + 1) % 4]];
    }

    for (int i = 1; i <= 6; i++) {
      dice[i] = temp[i];
    }

    if (map[y][x] == 0) {
      map[y][x] = dice[6];
    } else {
      dice[6] = map[y][x];
      map[y][x] = 0;
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