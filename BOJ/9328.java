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

  static int h, w;
  static char[][] map = new char[102][102];
  static boolean[] keys = new boolean[26];
  static boolean[][] visited = new boolean[102][102];
  static Queue<Pos>[] doors = new LinkedList[26];

  static int bfs() {
    Queue<Pos> q = new LinkedList<>();

    q.add(new Pos(0, 0));
    visited[0][0] = true;

    int result = 0;
    while (!q.isEmpty()) {
      Pos cur = q.peek();
      q.remove();

      for (int k = 0; k < 4; k++) {
        int xn = cur.x + dx[k];
        int yn = cur.y + dy[k];

        if (xn < 0 || yn < 0 || xn > w + 1 || yn > h + 1) {
          continue;
        }

        if (map[yn][xn] == '*' || visited[yn][xn]) {
          continue;
        }

        char next = map[yn][xn];
        if (Character.isLowerCase(next)) {
          q.add(new Pos(xn, yn));
          visited[yn][xn] = true;

          keys[next - 'a'] = true;
          while (!doors[next - 'a'].isEmpty()) {
            q.add(doors[next - 'a'].peek());
            doors[next - 'a'].remove();
          }
        } else if (Character.isUpperCase(next)) {
          if (keys[next - 'A']) {
            q.add(new Pos(xn, yn));
            visited[yn][xn] = true;
          } else {
            doors[next - 'A'].add(new Pos(xn, yn));
            visited[yn][xn] = true;
          }
        } else {
          if (map[yn][xn] == '$') {
            result += 1;
          }
          q.add(new Pos(xn, yn));
          visited[yn][xn] = true;
        }
      }
    }

    return result;
  }

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    int T = in.nextInt();

    while (T-- > 0) {
      h = in.nextInt();
      w = in.nextInt();

      for (char[] x : map) {
        Arrays.fill(x, '.');
      }
      for (int y = 1; y <= h; y++) {
        char[] s = in.next().toCharArray();

        for (int x = 1; x <= w; x++) {
          map[y][x] = s[x - 1];
        }
      }

      Arrays.fill(keys, false);
      for (char c : in.next().toCharArray()) {
        if (c == '0') {
          break;
        }

        keys[c - 'a'] = true;
      }

      for (boolean[] x : visited) {
        Arrays.fill(x, false);
      }
      for (int i = 0; i < 26; i++) {
        doors[i] = new LinkedList<>();
      }

      out.println(bfs());
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