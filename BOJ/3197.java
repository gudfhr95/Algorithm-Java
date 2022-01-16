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
  static int[] dy = {1, 0, -1, 0};

  static int R, C;
  static Pos start, end;
  static char[][] lake = new char[1500][1500];

  static Queue<Pos> swan = new LinkedList<>();
  static Queue<Pos> swanNext = new LinkedList<>();
  static boolean[][] swanVisited = new boolean[1500][1500];

  static Queue<Pos> water = new LinkedList<>();
  static Queue<Pos> waterNext = new LinkedList<>();
  static boolean[][] waterVisited = new boolean[1500][1500];

  static void melt() {
    while (!water.isEmpty()) {
      Pos cur = water.peek();
      water.remove();

      lake[cur.y][cur.x] = '.';

      for (int k = 0; k < 4; k++) {
        int xn = cur.x + dx[k];
        int yn = cur.y + dy[k];

        if (xn < 0 || yn < 0 || xn > C - 1 || yn > R - 1) {
          continue;
        }

        if (waterVisited[yn][xn]) {
          continue;
        }

        if (lake[yn][xn] == '.') {
          water.add(new Pos(xn, yn));
          waterVisited[yn][xn] = true;
        } else {
          waterNext.add(new Pos(xn, yn));
          waterVisited[yn][xn] = true;
        }
      }
    }

    water = waterNext;
    waterNext = new LinkedList<>();
  }

  static void move() {
    while (!swan.isEmpty()) {
      Pos cur = swan.peek();
      swan.remove();

      for (int k = 0; k < 4; k++) {
        int xn = cur.x + dx[k];
        int yn = cur.y + dy[k];

        if (xn < 0 || yn < 0 || xn > C - 1 || yn > R - 1) {
          continue;
        }

        if (swanVisited[yn][xn]) {
          continue;
        }

        if (lake[yn][xn] == '.') {
          swan.add(new Pos(xn, yn));
          swanVisited[yn][xn] = true;
        } else {
          swanNext.add(new Pos(xn, yn));
          swanVisited[yn][xn] = true;
        }
      }
    }

    swan = swanNext;
    swanNext = new LinkedList<>();
  }

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    R = in.nextInt();
    C = in.nextInt();

    for (int y = 0; y < R; y++) {
      char[] s = in.next().toCharArray();

      for (int x = 0; x < C; x++) {
        if (s[x] == 'L') {
          if (start == null) {
            start = new Pos(x, y);

            swan.add(new Pos(x, y));
            swanVisited[y][x] = true;
          } else {
            end = new Pos(x, y);
          }
        }

        if (s[x] != 'X') {
          water.add(new Pos(x, y));
          waterVisited[y][x] = true;
        }

        lake[y][x] = s[x];
      }
    }

    for (int d = 0; ; d++) {
      melt();
      move();

      if (swanVisited[end.y][end.x]) {
        out.println(d);
        break;
      }
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
