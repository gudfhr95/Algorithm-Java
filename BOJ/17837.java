import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

class Piece {

  int x, y, d, i;

  public Piece(int x, int y, int d, int i) {
    this.x = x;
    this.y = y;
    this.d = d;
    this.i = i;
  }
}

public class Main {

  static int[] dx = {0, 1, -1, 0, 0};
  static int[] dy = {0, 0, 0, -1, 1};

  static int N;
  static boolean result = false;
  static List<Piece> pieces = new ArrayList<>();
  static int[][] board = new int[13][13];
  static List<Piece>[][] state = new ArrayList[13][13];

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    N = in.nextInt();
    int K = in.nextInt();

    for (int y = 1; y <= N; y++) {
      for (int x = 1; x <= N; x++) {
        board[y][x] = in.nextInt();
        state[y][x] = new ArrayList<>();
      }
    }

    while (K-- > 0) {
      int y = in.nextInt();
      int x = in.nextInt();
      int d = in.nextInt();

      Piece p = new Piece(x, y, d, 0);
      pieces.add(p);
      state[y][x].add(p);
    }

    for (int t = 1; t <= 1000; t++) {
      simulate();

      if (result) {
        out.println(t);

        out.flush();

        return;
      }
    }

    out.println(-1);

    out.flush();
  }

  static void simulate() {
    for (Piece p : pieces) {
      int xn = p.x + dx[p.d];
      int yn = p.y + dy[p.d];

      if (xn < 1 || yn < 1 || xn > N || yn > N) {
        moveBlue(p);
      } else if (board[yn][xn] == 0) {
        moveWhite(p);
      } else if (board[yn][xn] == 1) {
        moveRed(p);
      } else if (board[yn][xn] == 2) {
        moveBlue(p);
      }
    }
  }

  static void moveWhite(Piece p) {
    int xn = p.x + dx[p.d];
    int yn = p.y + dy[p.d];

    state[yn][xn].addAll(new ArrayList<>(state[p.y][p.x].subList(p.i, state[p.y][p.x].size())));
    state[p.y][p.x] = new ArrayList<>(state[p.y][p.x].subList(0, p.i));

    updatePos(xn, yn);
  }

  static void moveRed(Piece p) {
    int xn = p.x + dx[p.d];
    int yn = p.y + dy[p.d];

    List<Piece> moving = state[p.y][p.x].subList(p.i, state[p.y][p.x].size());
    Collections.reverse(moving);

    state[yn][xn].addAll(new ArrayList<>(moving));
    state[p.y][p.x] = new ArrayList<>(state[p.y][p.x].subList(0, p.i));

    updatePos(xn, yn);
  }

  static void moveBlue(Piece p) {
    if (p.d == 1) {
      p.d = 2;
    } else if (p.d == 2) {
      p.d = 1;
    } else if (p.d == 3) {
      p.d = 4;
    } else if (p.d == 4) {
      p.d = 3;
    }

    int xn = p.x + dx[p.d];
    int yn = p.y + dy[p.d];

    if (xn < 1 || yn < 1 || xn > N || yn > N) {
      return;
    }

    if (board[yn][xn] == 2) {
      return;
    }

    if (board[yn][xn] == 0) {
      moveWhite(p);
    } else if (board[yn][xn] == 1) {
      moveRed(p);
    }

    updatePos(xn, yn);
  }

  static void updatePos(int x, int y) {
    if (state[y][x].size() >= 4) {
      result = true;
    }

    for (int i = 0; i < state[y][x].size(); i++) {
      Piece p = state[y][x].get(i);

      p.x = x;
      p.y = y;
      p.i = i;
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