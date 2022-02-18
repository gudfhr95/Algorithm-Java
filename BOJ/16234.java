import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
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

  static final int[] dx = {0, 1, 0, -1};
  static final int[] dy = {1, 0, -1, 0};

  static int N, L, R;
  static int[][] population = new int[50][50];
  static boolean[][] visited = new boolean[50][50];

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    N = in.nextInt();
    L = in.nextInt();
    R = in.nextInt();

    for (int y = 0; y < N; y++) {
      for (int x = 0; x < N; x++) {
        population[y][x] = in.nextInt();
      }
    }

    out.println(simulate());

    out.flush();
  }

  static int simulate() {
    int result = 0;

    while (true) {
      boolean moved = false;
      init();

      for (int y = 0; y < N; y++) {
        for (int x = 0; x < N; x++) {
          if (!visited[y][x] && bfs(x, y)) {
            moved = true;
          }
        }
      }

      if (!moved) {
        return result;
      }

      result += 1;
    }
  }

  static void init() {
    for (boolean[] a1 : visited) {
      Arrays.fill(a1, false);
    }
  }

  static boolean bfs(int x, int y) {
    Queue<Pos> q = new LinkedList<>();
    ArrayList<Pos> union = new ArrayList<>();

    q.add(new Pos(x, y));
    visited[y][x] = true;
    union.add(new Pos(x, y));

    while (!q.isEmpty()) {
      Pos cur = q.remove();

      for (int k = 0; k < 4; k++) {
        int xn = cur.x + dx[k];
        int yn = cur.y + dy[k];

        if (xn < 0 || yn < 0 || xn > N - 1 || yn > N - 1) {
          continue;
        }

        int diff = Math.abs(population[cur.y][cur.x] - population[yn][xn]);
        if (visited[yn][xn] || diff < L || diff > R) {
          continue;
        }

        q.add(new Pos(xn, yn));
        visited[yn][xn] = true;
        union.add(new Pos(xn, yn));
      }
    }

    if (union.size() == 1) {
      return false;
    }

    int sum = 0;
    for (Pos p : union) {
      sum += population[p.y][p.x];
    }

    for (Pos p : union) {
      population[p.y][p.x] = sum / union.size();
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