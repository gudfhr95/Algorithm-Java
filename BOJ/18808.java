import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Main {

  static int N, M;
  static int[][] notebook = new int[40][40];

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    N = in.nextInt();
    M = in.nextInt();
    int K = in.nextInt();

    for (int i = 0; i < K; i++) {
      int R = in.nextInt();
      int C = in.nextInt();

      int[][] sticker = new int[R][C];
      for (int y = 0; y < R; y++) {
        for (int x = 0; x < C; x++) {
          sticker[y][x] = in.nextInt();
        }
      }

      simulate(sticker);
    }

    int result = 0;
    for (int y = 0; y < N; y++) {
      for (int x = 0; x < M; x++) {
        result += notebook[y][x];
      }
    }

    out.println(result);

    out.flush();
  }

  static void simulate(int[][] sticker) {
    for (int i = 0; i < 4; i++) {
      for (int y = 0; y <= N - sticker.length; y++) {
        for (int x = 0; x <= M - sticker[0].length; x++) {
          if (isFit(sticker, x, y)) {
            stick(sticker, x, y);
            return;
          }
        }
      }
      sticker = rotate(sticker);
    }
  }

  static boolean isFit(int[][] sticker, int x, int y) {
    for (int r = 0; r < sticker.length; r++) {
      for (int c = 0; c < sticker[0].length; c++) {
        if (sticker[r][c] == 1 && notebook[y + r][c + x] == 1) {
          return false;
        }
      }
    }
    return true;
  }

  static void stick(int[][] sticker, int x, int y) {
    for (int r = 0; r < sticker.length; r++) {
      for (int c = 0; c < sticker[0].length; c++) {
        if (sticker[r][c] == 1) {
          notebook[y + r][c + x] = 1;
        }
      }
    }
  }

  static int[][] rotate(int[][] sticker) {
    int[][] result = new int[sticker[0].length][sticker.length];
    for (int y = 0; y < sticker.length; y++) {
      for (int x = 0; x < sticker[0].length; x++) {
        result[x][sticker.length - 1 - y] = sticker[y][x];
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