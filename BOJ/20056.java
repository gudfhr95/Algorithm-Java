import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Fireball {

  int m, s, d;

  public Fireball(int m, int s, int d) {
    this.m = m;
    this.s = s;
    this.d = d;
  }
}

public class Main {

  static final int[] dx = {0, 1, 1, 1, 0, -1, -1, -1};
  static final int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};

  static int N;
  static ArrayList<Fireball>[][] board;

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    N = in.nextInt();
    int M = in.nextInt();
    int K = in.nextInt();

    board = makeEmptyBoard();

    while (M-- > 0) {
      int y = in.nextInt() - 1;
      int x = in.nextInt() - 1;
      int m = in.nextInt();
      int s = in.nextInt();
      int d = in.nextInt();

      board[y][x].add(new Fireball(m, s, d));
    }

    while (K-- > 0) {
      move();
      merge();
    }

    int result = 0;
    for (int y = 0; y < N; y++) {
      for (int x = 0; x < N; x++) {
        result += getTotalMass(board[y][x]);
      }
    }

    out.println(result);

    out.flush();
  }

  static void move() {
    ArrayList<Fireball>[][] result = makeEmptyBoard();

    for (int y = 0; y < N; y++) {
      for (int x = 0; x < N; x++) {
        for (Fireball fireball : board[y][x]) {
          int xn = ((x + dx[fireball.d] * fireball.s) + (N * 1000)) % N;
          int yn = ((y + dy[fireball.d] * fireball.s) + (N * 1000)) % N;

          result[yn][xn].add(fireball);
        }
      }
    }

    board = result;
  }

  static void merge() {
    for (int y = 0; y < N; y++) {
      for (int x = 0; x < N; x++) {
        if (board[y][x].size() < 2) {
          continue;
        }

        int mass = getTotalMass(board[y][x]) / 5;
        if (mass == 0) {
          board[y][x].clear();
          continue;
        }

        int speed = getTotalSpeed(board[y][x]) / board[y][x].size();

        int dir = 1;
        if (hasSameMod(board[y][x], 0) || hasSameMod(board[y][x], 1)) {
          dir = 0;
        }

        board[y][x].clear();
        for (; dir < 8; dir += 2) {
          board[y][x].add(new Fireball(mass, speed, dir));
        }
      }
    }
  }

  static int getTotalMass(ArrayList<Fireball> fireballs) {
    int result = 0;
    for (Fireball fireball : fireballs) {
      result += fireball.m;
    }

    return result;
  }

  static int getTotalSpeed(ArrayList<Fireball> fireballs) {
    int result = 0;
    for (Fireball fireball : fireballs) {
      result += fireball.s;
    }

    return result;
  }

  static boolean hasSameMod(ArrayList<Fireball> fireballs, int i) {
    for (Fireball fireball : fireballs) {
      if (fireball.d % 2 != i) {
        return false;
      }
    }
    return true;
  }

  static ArrayList<Fireball>[][] makeEmptyBoard() {
    ArrayList<Fireball>[][] result = new ArrayList[N][N];
    for (int y = 0; y < N; y++) {
      for (int x = 0; x < N; x++) {
        result[y][x] = new ArrayList<>();
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
