import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
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

  static int[] dx = {0, 1, 0, -1};
  static int[] dy = {1, 0, -1, 0};

  static int M, N;
  static boolean paper[][] = new boolean[100][100];

  static int bfs(int x, int y) {
    Queue<Pos> queue = new LinkedList<>();

    queue.add(new Pos(x, y));
    paper[y][x] = true;

    int result = 1;
    while (!queue.isEmpty()) {
      Pos cur = queue.peek();
      queue.remove();

      for (int k = 0; k < 4; k++) {
        int xn = cur.x + dx[k];
        int yn = cur.y + dy[k];

        if (xn < 0 || yn < 0 || xn > N - 1 || yn > M - 1) {
          continue;
        }

        if (paper[yn][xn]) {
          continue;
        }

        queue.add(new Pos(xn, yn));
        paper[yn][xn] = true;
        result += 1;
      }
    }

    return result;
  }

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    M = in.nextInt();
    N = in.nextInt();
    int K = in.nextInt();

    for (int k = 0; k < K; k++) {
      int x1 = in.nextInt();
      int y1 = in.nextInt();
      int x2 = in.nextInt();
      int y2 = in.nextInt();

      for (int y = y1; y < y2; y++) {
        for (int x = x1; x < x2; x++) {
          paper[y][x] = true;
        }
      }
    }

    int count = 0;
    ArrayList<Integer> results = new ArrayList<>();
    for (int y = 0; y < M; y++) {
      for (int x = 0; x < N; x++) {
        if (!paper[y][x]) {
          count += 1;
          results.add(bfs(x, y));
        }
      }
    }

    Collections.sort(results);

    out.println(count);
    for (Integer result : results) {
      out.printf("%d ", result);
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