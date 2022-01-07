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

  Pos(int x, int y) {
    this.x = x;
    this.y = y;
  }
}

public class Main {

  static int[] dx = {0, 1, 0, -1};
  static int[] dy = {1, 0, -1, 0};

  static char[][] maze = new char[1001][1001];
  static int[][] dist = new int[1001][1001];
  static int[][] time = new int[1001][1001];

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    int R = in.nextInt();
    int C = in.nextInt();
    for (int y = 0; y < R; y++) {
      maze[y] = in.next().toCharArray();
    }

    Queue<Pos> fireQueue = new LinkedList<>();
    Queue<Pos> jihunQueue = new LinkedList<>();
    for (int[] x : dist) {
      Arrays.fill(x, -1);
    }
    for (int[] x : time) {
      Arrays.fill(x, -1);
    }
    for (int y = 0; y < R; y++) {
      for (int x = 0; x < C; x++) {
        if (maze[y][x] == 'F') {
          fireQueue.add(new Pos(x, y));
          dist[y][x] = 0;
        } else if (maze[y][x] == 'J') {
          jihunQueue.add(new Pos(x, y));
          time[y][x] = 0;
        }
      }
    }

    while (!fireQueue.isEmpty()) {
      Pos current = fireQueue.peek();
      fireQueue.remove();

      for (int k = 0; k < 4; k++) {
        int xn = current.x + dx[k];
        int yn = current.y + dy[k];

        if (xn < 0 || yn < 0 || xn > C - 1 || yn > R - 1) {
          continue;
        }

        if (maze[yn][xn] == '#' || dist[yn][xn] != -1) {
          continue;
        }

        fireQueue.add(new Pos(xn, yn));
        dist[yn][xn] = dist[current.y][current.x] + 1;
      }
    }

    while (!jihunQueue.isEmpty()) {
      Pos current = jihunQueue.peek();
      jihunQueue.remove();

      for (int k = 0; k < 4; k++) {
        int xn = current.x + dx[k];
        int yn = current.y + dy[k];

        if (xn < 0 || yn < 0 || xn > C - 1 || yn > R - 1) {
          continue;
        }

        if (maze[yn][xn] == '#' || time[yn][xn] != -1) {
          continue;
        }

        if (dist[yn][xn] != -1 && dist[yn][xn] <= time[current.y][current.x] + 1) {
          continue;
        }

        jihunQueue.add(new Pos(xn, yn));
        time[yn][xn] = time[current.y][current.x] + 1;
      }
    }

    int result = 987654321;
    for (int y = 0; y < R; y++) {
      for (int x = 0; x < C; x++) {
        if (x == 0 || y == 0 || x == C - 1 || y == R - 1) {
          if (time[y][x] == -1) {
            continue;
          }

          result = Math.min(result, time[y][x]);
        }
      }
    }

    if (result == 987654321) {
      out.println("IMPOSSIBLE");
    } else {
      out.println(result + 1);
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