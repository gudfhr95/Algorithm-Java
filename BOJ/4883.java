import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

  static int N;
  static int[][] graph = new int[100000][3];
  static int[][] d = new int[100000][3];

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    for (int t = 1; ; t++) {
      N = in.nextInt();

      if (N == 0) {
        break;
      }

      for (int y = 0; y < N; y++) {
        for (int x = 0; x < 3; x++) {
          graph[y][x] = in.nextInt();
        }
      }

      for (int[] a1 : d) {
        Arrays.fill(a1, -1);
      }

      out.printf("%d. %d\n", t, getMinCost(1, N - 1));
    }

    out.flush();
  }

  static int getMinCost(int x, int y) {
    if (y == 0) {
      if (x == 0) {
        return 987654321;
      } else if (x == 1) {
        return graph[y][x];
      } else if (x == 2) {
        return graph[y][x] + graph[y][x - 1];
      }
    }

    if (d[y][x] != -1) {
      return d[y][x];
    }

    if (x == 0) {
      int before = Math.min(getMinCost(0, y - 1), getMinCost(1, y - 1));
      d[y][x] = graph[y][x] + before;
    } else if (x == 1) {
      int before = Math.min(getMinCost(0, y), getMinCost(0, y - 1));
      before = Math.min(before, getMinCost(1, y - 1));
      before = Math.min(before, getMinCost(2, y - 1));
      d[y][x] = graph[y][x] + before;
    } else {
      int before = Math.min(getMinCost(1, y), getMinCost(1, y - 1));
      before = Math.min(before, getMinCost(2, y - 1));
      d[y][x] = graph[y][x] + before;
    }

    return d[y][x];
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