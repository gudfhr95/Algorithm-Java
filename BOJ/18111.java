import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Main {

  static int N, M, B;
  static int[][] height = new int[500][500];
  static int maxHeight = Integer.MIN_VALUE, minHeight = Integer.MAX_VALUE;

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    N = in.nextInt();
    M = in.nextInt();
    B = in.nextInt();

    for (int y = 0; y < N; y++) {
      for (int x = 0; x < M; x++) {
        height[y][x] = in.nextInt();

        minHeight = Math.min(minHeight, height[y][x]);
        maxHeight = Math.max(maxHeight, height[y][x]);
      }
    }

    int result = Integer.MAX_VALUE;
    int height = 0;
    for (int i = minHeight; i <= maxHeight; i++) {
      int time = countTime(i);

      if (time < result) {
        result = time;
        height = i;
      } else if (time == result) {
        height = Math.max(height, i);
      }
    }

    out.printf("%d %d", result, height);

    out.flush();
  }

  static int countTime(int n) {
    int result = 0;
    int blocks = B;

    for (int y = 0; y < N; y++) {
      for (int x = 0; x < M; x++) {
        if (height[y][x] > n) {
          int b = height[y][x] - n;

          result += 2 * b;
          blocks += b;
        }
      }
    }

    for (int y = 0; y < N; y++) {
      for (int x = 0; x < M; x++) {
        if (height[y][x] < n) {
          int c = n - height[y][x];

          if (blocks < c) {
            return Integer.MAX_VALUE;
          }

          result += c;
          blocks -= c;
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