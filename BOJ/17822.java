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

  static int[][] circle = new int[50][50];
  static boolean[][] visited = new boolean[50][50];

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    N = in.nextInt();
    M = in.nextInt();
    int T = in.nextInt();

    for (int y = 0; y < N; y++) {
      for (int x = 0; x < M; x++) {
        circle[y][x] = in.nextInt();
      }
    }

    while (T-- > 0) {
      simulate(in.nextInt(), in.nextInt(), in.nextInt());
    }

    int result = 0;
    for (int y = 0; y < N; y++) {
      for (int x = 0; x < M; x++) {
        result += circle[y][x];
      }
    }

    out.println(result);

    out.flush();
  }

  static void simulate(int x, int d, int k) {
    for (int y = 0; y < N; y++) {
      if ((y + 1) % x != 0) {
        continue;
      }

      int t = d == 0 ? 1 : M - 1;
      for (int i = 0; i < k; i++) {
        for (int j = 0; j < t; j++) {
          rotate(y);
        }
      }
    }

    for (boolean[] a1 : visited) {
      Arrays.fill(a1, false);
    }

    boolean erased = false;
    for (int r = 0; r < N; r++) {
      for (int c = 0; c < M; c++) {
        if (!visited[r][c] && circle[r][c] != 0 && bfs(c, r)) {
          erased = true;
        }
      }
    }

    if (!erased) {
      average();
    }
  }

  static void rotate(int y) {
    int temp = circle[y][0];
    for (int x = M - 1; x > 0; x--) {
      circle[y][(x + 1) % M] = circle[y][x];
    }
    circle[y][1] = temp;
  }

  static boolean bfs(int x, int y) {
    boolean result = false;
    Queue<Pos> q = new LinkedList<>();

    q.add(new Pos(x, y));
    visited[y][x] = true;

    int target = circle[y][x];
    while (!q.isEmpty()) {
      Pos cur = q.remove();
      circle[cur.y][cur.x] = 0;

      for (int k = 0; k < 4; k++) {
        int xn = (M + (cur.x + dx[k])) % M;
        int yn = cur.y + dy[k];

        if (xn < 0 || yn < 0 || xn > M - 1 || yn > N - 1) {
          continue;
        }

        if (visited[yn][xn] || circle[yn][xn] == 0 || circle[yn][xn] != target) {
          continue;
        }

        q.add(new Pos(xn, yn));
        visited[yn][xn] = true;

        result = true;
      }
    }

    if (!result) {
      circle[y][x] = target;
    }

    return result;
  }

  static void average() {
    float sum = 0.0f;
    int count = 0;
    for (int y = 0; y < N; y++) {
      for (int x = 0; x < M; x++) {
        if (circle[y][x] == 0) {
          continue;
        }

        sum += circle[y][x];
        count += 1;
      }
    }

    float average = sum / count;
    for (int y = 0; y < N; y++) {
      for (int x = 0; x < M; x++) {
        if (circle[y][x] == 0) {
          continue;
        }

        if (circle[y][x] > average) {
          circle[y][x] -= 1;
        } else if (circle[y][x] < average) {
          circle[y][x] += 1;
        }
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