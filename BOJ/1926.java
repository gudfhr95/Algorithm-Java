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

  Pos(int x, int y) {
    this.x = x;
    this.y = y;
  }
}

public class Main {

  static int[] dx = {0, 1, 0, -1};
  static int[] dy = {1, 0, -1, 0};

  static int n, m;
  static int[][] paper = new int[500][500];
  static boolean[][] visited = new boolean[500][500];

  static int getWidth(int x, int y) {
    Queue<Pos> queue = new LinkedList<>();

    queue.add(new Pos(x, y));
    visited[y][x] = true;

    int result = 1;
    while (!queue.isEmpty()) {
      Pos current = queue.peek();
      queue.remove();

      for (int k = 0; k < 4; k++) {
        int xn = current.x + dx[k];
        int yn = current.y + dy[k];

        if (xn < 0 || yn < 0 || xn > m - 1 || yn > n - 1) {
          continue;
        }

        if (paper[yn][xn] == 0 || visited[yn][xn]) {
          continue;
        }

        queue.add(new Pos(xn, yn));
        visited[yn][xn] = true;
        result += 1;
      }
    }

    return result;
  }

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    n = in.nextInt();
    m = in.nextInt();
    for (int y = 0; y < n; y++) {
      for (int x = 0; x < m; x++) {
        paper[y][x] = in.nextInt();
      }
    }

    int count = 0;
    int result = 0;
    for (int y = 0; y < n; y++) {
      for (int x = 0; x < m; x++) {
        if (!visited[y][x] && paper[y][x] == 1) {
          count += 1;
          result = Math.max(result, getWidth(x, y));
        }
      }
    }

    out.println(count);
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
