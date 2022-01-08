import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Pos {

  int x, y;

  public Pos(int x, int y) {
    this.x = x;
    this.y = y;
  }
}

public class Main {

  static int[] dx = {0, 1, 0, -1};
  static int[] dy = {1, 0, -1, 0};

  static int M, N;
  static int[][] land = new int[50][50];
  static boolean[][] visited = new boolean[50][50];

  static void bfs(int x, int y) {
    Queue<Pos> queue = new LinkedList<>();

    queue.add(new Pos(x, y));
    visited[y][x] = true;

    while (!queue.isEmpty()) {
      Pos current = queue.peek();
      queue.remove();

      for (int k = 0; k < 4; k++) {
        int xn = current.x + dx[k];
        int yn = current.y + dy[k];

        if (xn < 0 || yn < 0 || xn > M - 1 || yn > N - 1) {
          continue;
        }

        if (land[yn][xn] != 1 || visited[yn][xn]) {
          continue;
        }

        queue.add(new Pos(xn, yn));
        visited[yn][xn] = true;
      }
    }
  }

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    int T = in.nextInt();
    while (T-- > 0) {
      M = in.nextInt();
      N = in.nextInt();
      int K = in.nextInt();

      for (int[] x : land) {
        Arrays.fill(x, 0);
      }

      for (boolean[] x : visited) {
        Arrays.fill(x, false);
      }

      for (int k = 0; k < K; k++) {
        int X = in.nextInt();
        int Y = in.nextInt();

        land[Y][X] = 1;
      }

      int result = 0;
      for (int y = 0; y < N; y++) {
        for (int x = 0; x < M; x++) {
          if (land[y][x] == 1 && !visited[y][x]) {
            bfs(x, y);
            result += 1;
          }
        }
      }

      out.println(result);
    }

    out.flush();
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