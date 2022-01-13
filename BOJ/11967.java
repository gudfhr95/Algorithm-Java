import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
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
  static int[][] room = new int[101][101];
  static boolean[][] visited = new boolean[101][101];
  static ArrayList<Pos> light[][] = new ArrayList[101][101];

  static void turnOn() {
    Queue<Pos> q = new LinkedList<>();

    q.add(new Pos(1, 1));
    visited[1][1] = true;

    while (!q.isEmpty()) {
      Pos cur = q.peek();
      q.remove();

      for (Pos p : light[cur.y][cur.x]) {
        if (room[p.y][p.x] != 1) {
          room[p.y][p.x] = 1;

          boolean canVisit = false;
          for (int k = 0; k < 4; k++) {
            int xn = p.x + dx[k];
            int yn = p.y + dy[k];

            if (xn < 1 || yn < 1 || xn > N || yn > N) {
              continue;
            }

            if (visited[yn][xn]) {
              canVisit = true;
            }
          }

          if (canVisit) {
            q.add(new Pos(p.x, p.y));
            visited[p.y][p.x] = true;
          }
        }
      }

      for (int k = 0; k < 4; k++) {
        int xn = cur.x + dx[k];
        int yn = cur.y + dy[k];

        if (xn < 1 || yn < 1 || xn > N || yn > N) {
          continue;
        }

        if (room[yn][xn] == 0 || visited[yn][xn]) {
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
    int M = in.nextInt();

    for (int y = 1; y <= N; y++) {
      for (int x = 1; x <= N; x++) {
        light[y][x] = new ArrayList<>();
      }
    }

    while (M-- > 0) {
      int x = in.nextInt();
      int y = in.nextInt();
      int a = in.nextInt();
      int b = in.nextInt();

      light[y][x].add(new Pos(a, b));
    }

    room[1][1] = 1;
    turnOn();

    int result = 0;
    for (int y = 1; y <= N; y++) {
      for (int x = 1; x <= N; x++) {
        result += room[y][x];
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