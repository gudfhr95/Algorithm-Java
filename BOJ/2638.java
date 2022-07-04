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

  static final int[] dx = {0, 1, 0, -1};
  static final int[] dy = {-1, 0, 1, 0};

  static int N, M;
  static int[][] board = new int[100][100];

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    N = in.nextInt();
    M = in.nextInt();

    for (int y = 0; y < N; y++) {
      for (int x = 0; x < M; x++) {
        board[y][x] = in.nextInt();
      }
    }

    int result = 0;
    while (melt()) {
      result += 1;
    }

    out.println(result);

    out.flush();
  }

  static boolean melt() {
    Queue<Pos> q = new LinkedList<>();
    int[][] count = new int[N][M];

    q.add(new Pos(0, 0));
    count[0][0] += 1;
    q.add(new Pos(M - 1, 0));
    count[0][M - 1] += 1;
    q.add(new Pos(0, N - 1));
    count[N - 1][0] += 1;
    q.add(new Pos(M - 1, N - 1));
    count[N - 1][M - 1] += 1;

    boolean hasCheese = false;
    while (!q.isEmpty()) {
      Pos cur = q.remove();

      for (int k = 0; k < 4; k++) {
        int xn = cur.x + dx[k];
        int yn = cur.y + dy[k];

        if (xn < 0 || yn < 0 || xn > M - 1 || yn > N - 1) {
          continue;
        }

        if (board[yn][xn] == 1) {
          count[yn][xn] += 1;

          hasCheese = true;
          continue;
        }

        if (count[yn][xn] > 0) {
          continue;
        }

        q.add(new Pos(xn, yn));
        count[yn][xn] += 1;
      }
    }

    for (int y = 0; y < N; y++) {
      for (int x = 0; x < M; x++) {
        if (board[y][x] == 1 && count[y][x] >= 2) {
          board[y][x] = 0;
        }
      }
    }

    return hasCheese;
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