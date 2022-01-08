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

  int x, y, h;

  public Pos(int x, int y, int h) {
    this.x = x;
    this.y = y;
    this.h = h;
  }
}

public class Main {

  static int[] dx = {0, 1, 0, -1, 0, 0};
  static int[] dy = {1, 0, -1, 0, 0, 0};
  static int[] dh = {0, 0, 0, 0, 1, -1};

  static int M, N, H;
  static int[][][] tomato = new int[100][100][100];
  static int[][][] dist = new int[100][100][100];

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    M = in.nextInt();
    N = in.nextInt();
    H = in.nextInt();

    for (int[][] x : dist) {
      for (int[] y : x) {
        Arrays.fill(y, -1);
      }
    }

    Queue<Pos> queue = new LinkedList<>();
    for (int h = 0; h < H; h++) {
      for (int y = 0; y < N; y++) {
        for (int x = 0; x < M; x++) {
          tomato[h][y][x] = in.nextInt();

          if (tomato[h][y][x] == 1) {
            queue.add(new Pos(x, y, h));
            dist[h][y][x] = 0;
          }
        }
      }
    }

    while (!queue.isEmpty()) {
      Pos current = queue.peek();
      queue.remove();

      for (int k = 0; k < 6; k++) {
        int xn = current.x + dx[k];
        int yn = current.y + dy[k];
        int hn = current.h + dh[k];

        if (xn < 0 || yn < 0 || hn < 0 || xn > M - 1 || yn > N - 1 || hn > H - 1) {
          continue;
        }

        if (tomato[hn][yn][xn] == -1 || dist[hn][yn][xn] != -1) {
          continue;
        }

        queue.add(new Pos(xn, yn, hn));
        dist[hn][yn][xn] = dist[current.h][current.y][current.x] + 1;
      }
    }

    int result = 0;
    for (int h = 0; h < H; h++) {
      for (int y = 0; y < N; y++) {
        for (int x = 0; x < M; x++) {
          if (tomato[h][y][x] == 0 && dist[h][y][x] == -1) {
            out.println(-1);
            out.flush();

            return;
          }
          result = Math.max(result, dist[h][y][x]);
        }
      }
    }

    out.println(result);
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