import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Main {

  static final int[] dx = {0, 1, 0, -1};
  static final int[] dy = {-1, 0, 1, 0};

  static int N, M, r, c, d;
  static int[][] room = new int[50][50];
  static int[][] visited = new int[50][50];
  static int result = 0;

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    N = in.nextInt();
    M = in.nextInt();
    r = in.nextInt();
    c = in.nextInt();
    d = in.nextInt();

    for (int y = 0; y < N; y++) {
      for (int x = 0; x < M; x++) {
        room[y][x] = in.nextInt();
      }
    }

    simulate();

    out.println(result);

    out.flush();
  }

  static void simulate() {
    while (true) {
      if (visited[r][c] == 0) {
        visited[r][c] = 1;
        result += 1;
      }

      boolean move = false;
      int dn = d;
      for (int k = 0; k < 4; k++) {
        dn = (dn + 3) % 4;
        int xn = c + dx[dn];
        int yn = r + dy[dn];

        if (canMove(xn, yn) && visited[yn][xn] == 0) {
          r = yn;
          c = xn;
          d = dn;

          move = true;
          break;
        }
      }

      if (!move) {
        int xn = c + dx[(d + 2) % 4];
        int yn = r + dy[(d + 2) % 4];

        if (!canMove(xn, yn)) {
          return;
        }

        r = yn;
        c = xn;
      }
    }
  }

  static boolean canMove(int x, int y) {
    if (room[y][x] == 1) {
      return false;
    }

    return true;
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