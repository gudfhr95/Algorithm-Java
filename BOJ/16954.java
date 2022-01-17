import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Pos {

  int x, y, t;

  public Pos(int x, int y, int t) {
    this.x = x;
    this.y = y;
    this.t = t;
  }
}

public class Main {

  static int[] dx = {0, 0, 1, 1, 1, 0, -1, -1, -1};
  static int[] dy = {0, 1, 1, 0, -1, -1, -1, 0, 1};

  static char[][] board = new char[8][8];
  static boolean[][][] visited = new boolean[8][8][9];

  static int bfs() {
    Queue<Pos> q = new LinkedList<>();

    q.add(new Pos(0, 7, 0));
    visited[7][0][0] = true;

    while (!q.isEmpty()) {
      Pos cur = q.peek();
      q.remove();

      if (cur.x == 7 && cur.y == 0) {
        return 1;
      }

      for (int k = 0; k < 9; k++) {
        int xn = cur.x + dx[k];
        int yn = cur.y + dy[k];
        int tn = Math.min(cur.t + 1, 8);

        if (xn < 0 || yn < 0 || xn > 7 || yn > 7) {
          continue;
        }

        if (visited[yn][xn][tn]) {
          continue;
        }

        if (yn - cur.t >= 0 && board[yn - cur.t][xn] == '#') {
          continue;
        }

        if (yn - cur.t - 1 >= 0 && board[yn - cur.t - 1][xn] == '#') {
          continue;
        }

        q.add(new Pos(xn, yn, tn));
        visited[yn][xn][tn] = true;
      }
    }

    return 0;
  }

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    for (int y = 0; y < 8; y++) {
      board[y] = in.next().toCharArray();
    }

    out.println(bfs());

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
}
