import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
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

  static int N, M;
  static int[][] garden = new int[50][50];
  static ArrayList<Pos> land = new ArrayList<>();
  static int[][] color = new int[50][50];
  static int[][] time = new int[50][50];

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    N = in.nextInt();
    M = in.nextInt();
    int G = in.nextInt();
    int R = in.nextInt();

    for (int y = 0; y < N; y++) {
      for (int x = 0; x < M; x++) {
        garden[y][x] = in.nextInt();

        if (garden[y][x] == 2) {
          land.add(new Pos(x, y));
        }
      }
    }

    int[] mask = new int[land.size()];
    for (int i = land.size() - G - R; i < land.size() - R; i++) {
      mask[i] = 1;
    }
    for (int i = land.size() - R; i < land.size(); i++) {
      mask[i] = 2;
    }

    int result = 0;
    do {
      Queue<Pos> q = new LinkedList<>();
      for (int y = 0; y < N; y++) {
        for (int x = 0; x < M; x++) {
          color[y][x] = time[y][x] = 0;
        }
      }

      for (int i = 0; i < land.size(); i++) {
        if (mask[i] == 0) {
          continue;
        }

        Pos p = land.get(i);
        q.add(p);
        color[p.y][p.x] = mask[i];
      }

      result = Math.max(result, flower(q));
    } while (nextPermutation(mask));

    out.println(result);

    out.flush();
  }

  static int flower(Queue<Pos> q) {
    int result = 0;

    while (!q.isEmpty()) {
      Pos cur = q.remove();

      if (color[cur.y][cur.x] == 3) {
        continue;
      }

      for (int k = 0; k < 4; k++) {
        int xn = cur.x + dx[k];
        int yn = cur.y + dy[k];

        if (xn < 0 || yn < 0 || xn > M - 1 || yn > N - 1) {
          continue;
        }

        if (garden[yn][xn] == 0) {
          continue;
        }

        if (color[yn][xn] == 0) {
          q.add(new Pos(xn, yn));
          color[yn][xn] = color[cur.y][cur.x];
          time[yn][xn] = time[cur.y][cur.x] + 1;
        } else if (color[yn][xn] != 3 && color[yn][xn] != color[cur.y][cur.x]
            && time[yn][xn] == time[cur.y][cur.x] + 1) {
          color[yn][xn] = 3;
          result += 1;
        }
      }
    }
    return result;
  }

  static boolean nextPermutation(int[] a) {
    int i = a.length - 1;
    while (i > 0 && a[i - 1] >= a[i]) {
      i -= 1;
    }

    if (i <= 0) {
      return false;
    }

    int j = a.length - 1;
    while (a[j] <= a[i - 1]) {
      j -= 1;
    }

    swap(a, i - 1, j);

    j = a.length - 1;
    while (i < j) {
      swap(a, i++, j--);
    }

    return true;
  }

  static void swap(int[] a, int i, int j) {
    int temp = a[i];
    a[i] = a[j];
    a[j] = temp;
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