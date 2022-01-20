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

  static int[] dx = {0, 1, 0, -1};
  static int[] dy = {1, 0, -1, 0};

  static int w, h;
  static char[][] room = new char[20][20];

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    while (true) {
      w = in.nextInt();
      h = in.nextInt();

      if (w == 0 && h == 0) {
        break;
      }

      ArrayList<Pos> points = new ArrayList<>();
      for (int y = 0; y < h; y++) {
        char[] s = in.next().toCharArray();
        room[y] = s;

        for (int x = 0; x < w; x++) {
          if (room[y][x] == 'o') {
            points.add(0, new Pos(x, y));
          } else if (room[y][x] == '*') {
            points.add(new Pos(x, y));
          }
        }
      }

      out.println(solve(points));
    }

    out.flush();
  }

  static int solve(ArrayList<Pos> points) {
    int[][] dist = new int[20][20];

    for (int i = 0; i < points.size(); i++) {
      int[][] d = bfs(points.get(i));

      for (int j = 0; j < points.size(); j++) {
        dist[i][j] = d[points.get(j).y][points.get(j).x];

        if (dist[i][j] == -1) {
          return -1;
        }
      }
    }

    int[] trashes = new int[points.size() - 1];
    for (int i = 0; i < points.size() - 1; i++) {
      trashes[i] = i + 1;
    }

    int result = 987654321;
    do {
      int sum = 0;

      int cur = 0;
      for (int i = 0; i < trashes.length; i++) {
        sum += dist[cur][trashes[i]];

        cur = trashes[i];
      }

      result = Math.min(result, sum);
    } while (nextPermutation(trashes));

    return result;
  }

  static int[][] bfs(Pos start) {
    int[][] dist = new int[20][20];
    for (int[] x : dist) {
      Arrays.fill(x, -1);
    }

    Queue<Pos> q = new LinkedList<>();

    q.add(start);
    dist[start.y][start.x] = 0;

    while (!q.isEmpty()) {
      Pos cur = q.peek();
      q.remove();

      for (int k = 0; k < 4; k++) {
        int xn = cur.x + dx[k];
        int yn = cur.y + dy[k];

        if (xn < 0 || yn < 0 || xn > w - 1 || yn > h - 1) {
          continue;
        }

        if (room[yn][xn] == 'x' || dist[yn][xn] != -1) {
          continue;
        }

        q.add(new Pos(xn, yn));
        dist[yn][xn] = dist[cur.y][cur.x] + 1;
      }
    }

    return dist;
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

  static void swap(int[] a, int index1, int index2) {
    int temp = a[index1];
    a[index1] = a[index2];
    a[index2] = temp;
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
