import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Pos {

  int x, y, d;

  public Pos(int x, int y, int d) {
    this.x = x;
    this.y = y;
    this.d = d;
  }
}

public class Main {

  static final int[] dx = {0, 1, 0, -1};
  static final int[] dy = {1, 0, -1, 0};

  static int N, M, P;
  static int[] S = new int[10];
  static int[][] board = new int[1000][1000];
  static int[] result = new int[10];

  static Queue<Pos>[] q = new LinkedList[10];
  static Queue<Pos>[] nextQ = new LinkedList[10];

  static void bfs() {
    for (int i = 1; i <= P; i++) {
      q[i] = new LinkedList<>();
      nextQ[i] = new LinkedList<>();
    }

    for (int y = 0; y < N; y++) {
      for (int x = 0; x < M; x++) {
        if (board[y][x] <= 0) {
          continue;
        }

        q[board[y][x]].add(new Pos(x, y, 0));
        result[board[y][x]] += 1;
      }
    }

    while (true) {
      boolean canExtend = false;

      for (int i = 1; i <= P; i++) {
        while (!q[i].isEmpty()) {
          Pos cur = q[i].peek();
          q[i].remove();

          for (int k = 0; k < 4; k++) {
            int xn = cur.x + dx[k];
            int yn = cur.y + dy[k];
            int dn = cur.d + 1;

            if (xn < 0 || yn < 0 || xn > M - 1 || yn > N - 1) {
              continue;
            }

            if (board[yn][xn] != 0 || dn > S[i]) {
              continue;
            }

            board[yn][xn] = i;
            q[i].add(new Pos(xn, yn, dn));
            result[i] += 1;

            nextQ[i].add(new Pos(xn, yn, 0));

            canExtend = true;
          }
        }

        q[i] = nextQ[i];
        nextQ[i] = new LinkedList<>();
      }

      if (!canExtend) {
        return;
      }
    }
  }

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    N = in.nextInt();
    M = in.nextInt();
    P = in.nextInt();

    for (int i = 1; i <= P; i++) {
      S[i] = in.nextInt();
    }

    for (int y = 0; y < N; y++) {
      char[] s = in.next().toCharArray();

      for (int x = 0; x < M; x++) {
        if (s[x] == '.') {
          board[y][x] = 0;
        } else if (s[x] == '#') {
          board[y][x] = -1;
        } else {
          board[y][x] = s[x] - '0';
        }
      }
    }

    bfs();

    for (int i = 1; i <= P; i++) {
      out.printf("%d ", result[i]);
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
