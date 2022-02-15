import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Deque;
import java.util.LinkedList;
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

  static final int SNAKE = 1, APPLE = 2;
  static final int[] dx = {0, 1, 0, -1};
  static final int[] dy = {-1, 0, 1, 0};

  static int N, K, L;
  static int[][] board = new int[101][101];
  static char[] dir = new char[10001];
  static Deque<Pos> snake = new LinkedList<>();

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    N = in.nextInt();
    K = in.nextInt();

    for (int k = 0; k < K; k++) {
      int y = in.nextInt();
      int x = in.nextInt();

      board[y][x] = APPLE;
    }

    L = in.nextInt();
    for (int l = 0; l < L; l++) {
      dir[in.nextInt()] = in.next().toCharArray()[0];
    }

    snake.addFirst(new Pos(1, 1, 1));
    board[1][1] = SNAKE;

    out.println(simulate());

    out.flush();
  }

  static int simulate() {
    int time = 0;

    while (true) {
      Pos cur = snake.peekFirst();

      int dn = cur.d;
      if (dir[time] == 'L') {
        dn = (dn + 3) % 4;
      } else if (dir[time] == 'D') {
        dn = (dn + 1) % 4;
      }

      int xn = cur.x + dx[dn];
      int yn = cur.y + dy[dn];

      if (!canMove(xn, yn)) {
        break;
      }

      move(xn, yn, dn);

      time += 1;
    }

    return time + 1;
  }

  static boolean canMove(int x, int y) {
    if (x < 1 || y < 1 || x > N || y > N) {
      return false;
    }

    if (board[y][x] == SNAKE) {
      return false;
    }

    return true;
  }

  static void move(int x, int y, int d) {
    if (board[y][x] != APPLE) {
      Pos tail = snake.removeLast();
      board[tail.y][tail.x] = 0;
    }
    snake.addFirst(new Pos(x, y, d));
    board[y][x] = SNAKE;
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