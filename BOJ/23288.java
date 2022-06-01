import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
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
  static int[] dy = {-1, 0, 1, 0};

  static int N, M;
  static int[][] map = new int[20][20];

  static Pos p = new Pos(0, 0);
  static int d = 1;
  static int[] num = {2, 4, 1, 3, 5, 6};
  static int[][] idx = {
      {2, 4, 5, 0},
      {2, 1, 5, 3},
      {2, 0, 5, 4},
      {2, 3, 5, 1}
  };

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    N = in.nextInt();
    M = in.nextInt();
    int K = in.nextInt();

    for (int y = 0; y < N; y++) {
      for (int x = 0; x < M; x++) {
        map[y][x] = in.nextInt();
      }
    }

    int result = 0;
    while (K-- > 0) {
      move();
      result += getScore();
    }

    out.println(result);

    out.flush();
  }

  static void move() {
    int xn = p.x + dx[d];
    int yn = p.y + dy[d];

    if (xn < 0 || yn < 0 || xn > M - 1 || yn > N - 1) {
      d = (d + 2) % 4;
    }

    p.x += dx[d];
    p.y += dy[d];

    roll(d);

    if (num[5] > map[p.y][p.x]) {
      d = (d + 1) % 4;
    } else if (num[5] < map[p.y][p.x]) {
      d = (d + 3) % 4;
    }
  }

  static void roll(int d) {
    int[] result = new int[6];

    for (int i = 0; i < 6; i++) {
      result[i] = num[i];
    }

    for (int i = 0; i < 4; i++) {
      result[idx[d][i]] = num[idx[d][(i + 1) % 4]];
    }

    for (int i = 0; i < 6; i++) {
      num[i] = result[i];
    }
  }

  static int getScore() {
    Queue<Pos> q = new LinkedList<>();
    boolean[][] visited = new boolean[N][M];

    q.add(p);
    visited[p.y][p.x] = true;

    int result = 1;
    while (!q.isEmpty()) {
      Pos cur = q.remove();

      for (int k = 0; k < 4; k++) {
        int xn = cur.x + dx[k];
        int yn = cur.y + dy[k];

        if (xn < 0 || yn < 0 || xn > M - 1 || yn > N - 1) {
          continue;
        }

        if (map[yn][xn] != map[cur.y][cur.x]) {
          continue;
        }

        if (visited[yn][xn]) {
          continue;
        }

        q.add(new Pos(xn, yn));
        visited[yn][xn] = true;
        result += 1;
      }
    }

    return result * map[p.y][p.x];
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