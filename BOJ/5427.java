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

  static int w, h;
  static char[][] map = new char[1000][1000];
  static int[][] fireDist = new int[1000][1000];
  static int[][] personDist = new int[1000][1000];

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    int T = in.nextInt();
    while (T-- > 0) {
      w = in.nextInt();
      h = in.nextInt();

      Queue<Pos> fireQueue = new LinkedList<>();
      Queue<Pos> personQueue = new LinkedList<>();
      for (int[] x : fireDist) {
        Arrays.fill(x, -1);
      }
      for (int[] x : personDist) {
        Arrays.fill(x, -1);
      }

      for (int y = 0; y < h; y++) {
        map[y] = in.next().toCharArray();

        for (int x = 0; x < w; x++) {
          if (map[y][x] == '*') {
            fireQueue.add(new Pos(x, y));
            fireDist[y][x] = 0;
          } else if (map[y][x] == '@') {
            personQueue.add(new Pos(x, y));
            personDist[y][x] = 0;
          }
        }
      }

      while (!fireQueue.isEmpty()) {
        Pos current = fireQueue.peek();
        fireQueue.remove();

        for (int k = 0; k < 4; k++) {
          int xn = current.x + dx[k];
          int yn = current.y + dy[k];

          if (xn < 0 || yn < 0 || xn > w - 1 || yn > h - 1) {
            continue;
          }

          if (map[yn][xn] == '#' || fireDist[yn][xn] != -1) {
            continue;
          }

          fireQueue.add(new Pos(xn, yn));
          fireDist[yn][xn] = fireDist[current.y][current.x] + 1;
        }
      }

      while (!personQueue.isEmpty()) {
        Pos current = personQueue.peek();
        personQueue.remove();

        for (int k = 0; k < 4; k++) {
          int xn = current.x + dx[k];
          int yn = current.y + dy[k];

          if (xn < 0 || yn < 0 || xn > w - 1 || yn > h - 1) {
            continue;
          }

          if (map[yn][xn] == '#' || personDist[yn][xn] != -1) {
            continue;
          }

          if (fireDist[yn][xn] != -1 && fireDist[yn][xn] <= personDist[current.y][current.x] + 1) {
            continue;
          }

          personQueue.add(new Pos(xn, yn));
          personDist[yn][xn] = personDist[current.y][current.x] + 1;
        }
      }

      int result = -1;
      for (int y = 0; y < h; y++) {
        for (int x = 0; x < w; x++) {
          if (x == 0 || y == 0 || x == w - 1 || y == h - 1) {
            if (personDist[y][x] == -1) {
              continue;
            }

            if (result == -1 || result > personDist[y][x]) {
              result = personDist[y][x];
            }
          }
        }
      }

      if (result == -1) {
        out.println("IMPOSSIBLE");
      } else {
        out.println(result + 1);
      }

      out.flush();
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
