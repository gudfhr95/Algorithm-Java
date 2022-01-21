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
  static int[] dy = {-1, 0, 1, 0};

  static int N, M;
  static int[][] castle = new int[50][50];
  static int[][] color = new int[50][50];
  static int[] size = new int[2501];

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    N = in.nextInt();
    M = in.nextInt();

    for (int y = 0; y < M; y++) {
      for (int x = 0; x < N; x++) {
        castle[y][x] = in.nextInt();
      }
    }

    int c = 1;
    for (int y = 0; y < M; y++) {
      for (int x = 0; x < N; x++) {
        if (color[y][x] == 0) {
          bfs(x, y, c++);
        }
      }
    }

    int roomCount = c - 1;
    out.println(roomCount);
    out.println(getLargestRoom(roomCount));
    out.println(getLargestSumOfTwoRoom());

    out.flush();
  }

  static void bfs(int x, int y, int c) {
    Queue<Pos> q = new LinkedList<>();

    q.add(new Pos(x, y));
    color[y][x] = c;

    int sum = 1;
    while (!q.isEmpty()) {
      Pos cur = q.peek();
      q.remove();

      for (int k : getNextDirection(cur)) {
        int xn = cur.x + dx[k];
        int yn = cur.y + dy[k];

        if (color[yn][xn] != 0) {
          continue;
        }

        q.add(new Pos(xn, yn));
        color[yn][xn] = c;
        sum += 1;
      }
    }

    size[c] = sum;
  }

  static ArrayList<Integer> getNextDirection(Pos p) {
    ArrayList<Integer> result = new ArrayList<>();

    int cur = castle[p.y][p.x];
    if ((cur & 0x1) == 0) {
      result.add(3);
    }

    if ((cur & 0x2) == 0) {
      result.add(0);
    }

    if ((cur & 0x4) == 0) {
      result.add(1);
    }

    if ((cur & 0x8) == 0) {
      result.add(2);
    }

    return result;
  }

  static int getLargestRoom(int roomCount) {
    int result = -1;

    for (int i = 1; i <= roomCount; i++) {
      result = Math.max(result, size[i]);
    }

    return result;
  }

  static int getLargestSumOfTwoRoom() {
    int result = -1;

    for (int y = 0; y < M; y++) {
      for (int x = 0; x < N; x++) {
        for (int k = 0; k < 4; k++) {
          int xn = x + dx[k];
          int yn = y + dy[k];

          if (xn < 0 || yn < 0 || xn > N - 1 || yn > M - 1) {
            continue;
          }

          if (color[y][x] == color[yn][xn]) {
            continue;
          }

          int sum = size[color[y][x]] + size[color[yn][xn]];
          result = Math.max(result, sum);
        }
      }
    }

    return result;
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
