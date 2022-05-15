import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Pos {

  int x, y;
  String s;

  public Pos(int x, int y, String s) {
    this.x = x;
    this.y = y;
    this.s = s;
  }
}

public class Main {

  static final int[] dx = {0, 1, 1, 1, 0, -1, -1, -1};
  static final int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};

  static int N, M, K;
  static char[][] board = new char[11][11];
  static HashMap<String, Integer> letters = new HashMap<>();

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    N = in.nextInt();
    M = in.nextInt();
    K = in.nextInt();
    for (int y = 0; y < N; y++) {
      board[y] = in.next().toCharArray();
    }

    for (int y = 0; y < N; y++) {
      for (int x = 0; x < M; x++) {
        bfs(x, y);
      }
    }

    while (K-- > 0) {
      String query = in.next();

      if (!letters.containsKey(query)) {
        out.println(0);
      } else {
        out.println(letters.get(query));
      }
    }

    out.flush();
  }

  static void bfs(int x, int y) {
    Queue<Pos> q = new LinkedList<>();

    q.add(new Pos(x, y, Character.toString(board[y][x])));
    putLetter(Character.toString(board[y][x]));

    while (!q.isEmpty()) {
      Pos cur = q.remove();

      for (int k = 0; k < 8; k++) {
        int xn = (cur.x + dx[k] + M) % M;
        int yn = (cur.y + dy[k] + N) % N;
        String sn = cur.s + board[yn][xn];

        if (sn.length() < 5) {
          q.add(new Pos(xn, yn, sn));
        }

        putLetter(sn);
      }
    }
  }

  static void putLetter(String s) {
    if (!letters.containsKey(s)) {
      letters.put(s, 0);
    }

    int count = letters.get(s);
    letters.replace(s, count + 1);
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