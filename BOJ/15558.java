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

  static int[] dx = {1, -1, 0};
  static int[] dy = {0, 0, 1};

  static int N, k;
  static char[][] map = new char[2][100000];
  static int[][] dist = new int[2][100000];

  public static void main(String args[]) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    N = in.nextInt();
    k = in.nextInt();
    dx[2] = k;

    for (int y = 0; y < 2; y++) {
      map[y] = in.next().toCharArray();
    }

    for (int[] a1 : dist) {
      Arrays.fill(a1, -1);
    }

    out.println(bfs());

    out.flush();
  }

  static int bfs() {
    Queue<Pos> q = new LinkedList<>();

    q.add(new Pos(0, 0));
    dist[0][0] = 0;

    while (!q.isEmpty()) {
      Pos cur = q.remove();

      for (int k = 0; k < 3; k++) {
        int xn = cur.x + dx[k];
        int yn = (cur.y + dy[k]) % 2;
        int dn = dist[cur.y][cur.x] + 1;

        if (xn >= N) {
          return 1;
        }

        if (xn < 0 || xn < dn) {
          continue;
        }

        if (map[yn][xn] == '0' || dist[yn][xn] != -1) {
          continue;
        }

        q.add(new Pos(xn, yn));
        dist[yn][xn] = dn;
      }
    }

    return 0;
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