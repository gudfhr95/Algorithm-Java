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
  static int[][] map = new int[100][100];
  static int[][] color = new int[100][100];
  static int[][] dist = new int[100][100];

  static void paint(int x, int y, int c) {
    Queue<Pos> q = new LinkedList<>();

    q.add(new Pos(x, y));
    color[y][x] = c;

    while (!q.isEmpty()) {
      Pos cur = q.peek();
      q.remove();

      for (int k = 0; k < 4; k++) {
        int xn = cur.x + dx[k];
        int yn = cur.y + dy[k];

        if (xn < 0 || yn < 0 || xn > N - 1 || yn > N - 1) {
          continue;
        }

        if (map[yn][xn] == 0 || color[yn][xn] != 0) {
          continue;
        }

        q.add(new Pos(xn, yn));
        color[yn][xn] = c;
      }
    }
  }

  static void expand() {
    Queue<Pos> q = new LinkedList<>();

    for (int y = 0; y < N; y++) {
      for (int x = 0; x < N; x++) {
        if (map[y][x] == 1) {
          q.add(new Pos(x, y));
          dist[y][x] = 0;
        }
      }
    }

    while (!q.isEmpty()) {
      Pos cur = q.peek();
      q.remove();

      for (int k = 0; k < 4; k++) {
        int xn = cur.x + dx[k];
        int yn = cur.y + dy[k];

        if (xn < 0 || yn < 0 || xn > N - 1 || yn > N - 1) {
          continue;
        }

        if (map[yn][xn] == 0 && dist[yn][xn] == -1 && color[yn][xn] == 0) {
          q.add(new Pos(xn, yn));
          dist[yn][xn] = dist[cur.y][cur.x] + 1;
          color[yn][xn] = color[cur.y][cur.x];
        }
      }
    }
  }

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    N = in.nextInt();
    for (int y = 0; y < N; y++) {
      for (int x = 0; x < N; x++) {
        map[y][x] = in.nextInt();
      }
    }

    int c = 1;
    for (int y = 0; y < N; y++) {
      for (int x = 0; x < N; x++) {
        if (map[y][x] == 1 && color[y][x] == 0) {
          paint(x, y, c++);
        }
      }
    }

    for (int[] x : dist) {
      Arrays.fill(x, -1);
    }

    expand();

    int result = 987654321;
    for (int y = 0; y < N; y++) {
      for (int x = 0; x < N; x++) {
        for (int k = 0; k < 4; k++) {
          int xn = x + dx[k];
          int yn = y + dy[k];

          if (xn < 0 || yn < 0 || xn > N - 1 || yn > N - 1) {
            continue;
          }

          if (color[y][x] != color[yn][xn]) {
            result = Math.min(result, dist[y][x] + dist[yn][xn]);
          }
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