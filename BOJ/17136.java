import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

  static int[][] paper = new int[10][10];
  static int result = -1;

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    for (int y = 0; y < 10; y++) {
      for (int x = 0; x < 10; x++) {
        paper[y][x] = in.nextInt();
      }
    }

    int[] count = {0, 0, 0, 0, 0, 0};
    fill(paper, 0, count);

    out.println(result);

    out.flush();
  }

  static void fill(int[][] p, int n, int[] count) {
    if (n == 100) {
      if (isFilled(p)) {
        int sum = 0;
        for (int i = 1; i <= 5; i++) {
          sum += count[i];
        }

        if (result == -1 || result > sum) {
          result = sum;
        }
      }

      return;
    }

    int x = n % 10;
    int y = n / 10;
    if (p[y][x] == 0) {
      fill(p, n + 1, count);

      return;
    }

    for (int i = 1; i <= 5; i++) {
      if (count[i] >= 5) {
        continue;
      }

      if (isFit(p, n, i)) {
        int[][] pNext = copy(p);
        int[] countNext = Arrays.copyOf(count, 6);

        patch(pNext, n, i);
        countNext[i] += 1;

        fill(pNext, n + 1, countNext);
      }
    }
  }

  static boolean isFilled(int[][] p) {
    for (int y = 0; y < 10; y++) {
      for (int x = 0; x < 10; x++) {
        if (p[y][x] == 1) {
          return false;
        }
      }
    }
    return true;
  }

  static boolean isFit(int[][] p, int n, int l) {
    int x = n % 10;
    int y = n / 10;

    for (int dy = 0; dy < l; dy++) {
      for (int dx = 0; dx < l; dx++) {
        int xn = x + dx;
        int yn = y + dy;

        if (xn < 0 || yn < 0 || xn > 9 || yn > 9) {
          return false;
        }

        if (p[yn][xn] == 0) {
          return false;
        }
      }
    }

    return true;
  }

  static int[][] copy(int[][] p) {
    int[][] result = new int[10][10];
    for (int y = 0; y < 10; y++) {
      for (int x = 0; x < 10; x++) {
        result[y][x] = p[y][x];
      }
    }

    return result;
  }

  static void patch(int[][] p, int n, int l) {
    int x = n % 10;
    int y = n / 10;

    for (int dy = 0; dy < l; dy++) {
      for (int dx = 0; dx < l; dx++) {
        p[y + dy][x + dx] = 0;
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