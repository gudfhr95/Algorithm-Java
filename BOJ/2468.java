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

  static int N;
  static int[][] area = new int[100][100];
  static boolean[][] visited = new boolean[100][100];

  static void bfs(int x, int y, int h) {
    Queue<Pos> q = new LinkedList<>();

    q.add(new Pos(x, y));
    visited[y][x] = true;

    while (!q.isEmpty()) {
      Pos cur = q.peek();
      q.remove();

      for (int k = 0; k < 4; k++) {
        int xn = cur.x + dx[k];
        int yn = cur.y + dy[k];

        if (xn < 0 || yn < 0 || xn > N - 1 || yn > N - 1) {
          continue;
        }

        if (area[yn][xn] <= h || visited[yn][xn]) {
          continue;
        }

        q.add(new Pos(xn, yn));
        visited[yn][xn] = true;
      }
    }
  }

  static int getSafeArea(int h) {
    for (boolean[] x : visited) {
      Arrays.fill(x, false);
    }

    int result = 0;
    for (int y = 0; y < N; y++) {
      for (int x = 0; x < N; x++) {
        if (area[y][x] > h && !visited[y][x]) {
          bfs(x, y, h);
          result += 1;
        }
      }
    }

    return result;
  }

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    N = in.nextInt();

    int maxHeight = 0;
    for (int y = 0; y < N; y++) {
      for (int x = 0; x < N; x++) {
        area[y][x] = in.nextInt();
        maxHeight = Math.max(maxHeight, area[y][x]);
      }
    }

    int result = 0;
    for (int h = 0; h <= maxHeight; h++) {
      result = Math.max(result, getSafeArea(h));
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