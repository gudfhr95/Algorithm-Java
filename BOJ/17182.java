import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Main {

  static int N, K;
  static int[][] T = new int[10][10];
  static boolean[] visited = new boolean[10];
  static int[] order = new int[10];
  static int result = Integer.MAX_VALUE;

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    N = in.nextInt();
    K = in.nextInt();

    for (int y = 0; y < N; y++) {
      for (int x = 0; x < N; x++) {
        T[y][x] = in.nextInt();
      }
    }

    floyd();

    getMinDist(0);

    out.println(result);

    out.flush();
  }

  static void floyd() {
    for (int k = 0; k < N; k++) {
      for (int y = 0; y < N; y++) {
        for (int x = 0; x < N; x++) {
          T[y][x] = Math.min(T[y][x], T[y][k] + T[k][x]);
        }
      }
    }
  }

  static void getMinDist(int n) {
    if (n == N) {
      if (order[0] != K) {
        return;
      }

      int sum = 0;
      for (int i = 0; i < N - 1; i++) {
        sum += T[order[i]][order[i + 1]];
      }

      result = Math.min(result, sum);
      return;
    }

    for (int i = 0; i < N; i++) {
      if (visited[i]) {
        continue;
      }

      visited[i] = true;
      order[n] = i;

      getMinDist(n + 1);

      visited[i] = false;
      order[n] = -1;
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