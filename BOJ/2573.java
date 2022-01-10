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

  static int N, M;
  static int[][] ice = new int[300][300];
  static boolean[][] visited = new boolean[300][300];

  static void melt(int x, int y) {
    Queue<Pos> q = new LinkedList<>();

    q.add(new Pos(x, y));
    visited[y][x] = true;

    while (!q.isEmpty()) {
      Pos cur = q.peek();
      q.remove();

      for (int k = 0; k < 4; k++) {
        int xn = cur.x + dx[k];
        int yn = cur.y + dy[k];

        if (xn < 0 || yn < 0 || xn > M - 1 || yn > N - 1) {
          continue;
        }

        if (visited[yn][xn]) {
          continue;
        }

        if (ice[yn][xn] <= 0) {
          ice[cur.y][cur.x] -= 1;
        } else if (ice[yn][xn] > 0) {
          q.add(new Pos(xn, yn));
          visited[yn][xn] = true;
        }
      }
    }
  }

  static void count(int x, int y) {
    Queue<Pos> q = new LinkedList<>();

    q.add(new Pos(x, y));
    visited[y][x] = true;

    while (!q.isEmpty()) {
      Pos cur = q.peek();
      q.remove();

      for (int k = 0; k < 4; k++) {
        int xn = cur.x + dx[k];
        int yn = cur.y + dy[k];

        if (xn < 0 || yn < 0 || xn > M - 1 || yn > N - 1) {
          continue;
        }

        if (ice[yn][xn] <= 0 || visited[yn][xn]) {
          continue;
        }

        q.add(new Pos(xn, yn));
        visited[yn][xn] = true;
      }
    }
  }

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    N = in.nextInt();
    M = in.nextInt();

    for (int y = 0; y < N; y++) {
      for (int x = 0; x < M; x++) {
        ice[y][x] = in.nextInt();
      }
    }

    int result = 1;
    while (true) {
      for (boolean[] x : visited) {
        Arrays.fill(x, false);
      }

      for (int y = 0; y < N; y++) {
        for (int x = 0; x < M; x++) {
          if (ice[y][x] > 0 && !visited[y][x]) {
            melt(x, y);
          }
        }
      }

      for (boolean[] x : visited) {
        Arrays.fill(x, false);
      }

      int cnt = 0;
      for (int y = 0; y < N; y++) {
        for (int x = 0; x < M; x++) {
          if (ice[y][x] > 0 && !visited[y][x]) {
            count(x, y);
            cnt += 1;
          }
        }
      }

      if (cnt >= 2) {
        out.println(result);
        break;
      } else if (cnt == 0) {
        out.println(0);
        break;
      }

      result += 1;
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
