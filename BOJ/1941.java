import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
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

  static char[][] seat = new char[5][5];

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    for (int i = 0; i < 5; i++) {
      seat[i] = in.next().toCharArray();
    }

    int result = 0;

    int[] mask = new int[25];
    for (int i = 7; i < 25; i++) {
      mask[i] = 1;
    }

    do {
      if (check(mask)) {
        result += 1;
      }
    } while (nextPermutation(mask));

    out.println(result);

    out.flush();
  }

  static boolean check(int[] mask) {
    Pos start = null;
    for (int i = 0; i < 25; i++) {
      if (mask[i] == 0) {
        start = new Pos(i % 5, i / 5);
        break;
      }
    }

    Queue<Pos> q = new LinkedList<>();
    boolean[][] visited = new boolean[5][5];
    int lee = 0;
    int lim = 0;

    q.add(start);
    visited[start.y][start.x] = true;
    if (seat[start.y][start.x] == 'S') {
      lee += 1;
    } else {
      lim += 1;
    }

    while (!q.isEmpty()) {
      Pos cur = q.remove();

      for (int k = 0; k < 4; k++) {
        int xn = cur.x + dx[k];
        int yn = cur.y + dy[k];

        if (xn < 0 || yn < 0 || xn > 4 || yn > 4) {
          continue;
        }

        if (visited[yn][xn] || mask[yn * 5 + xn] == 1) {
          continue;
        }

        q.add(new Pos(xn, yn));
        visited[yn][xn] = true;
        if (seat[yn][xn] == 'S') {
          lee += 1;
        } else {
          lim += 1;
        }
      }
    }

    return ((lee + lim) == 7) && (lee >= 4);
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
}