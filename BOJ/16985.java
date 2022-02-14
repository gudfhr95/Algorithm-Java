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

  int x, y, z;

  public Pos(int x, int y, int z) {
    this.x = x;
    this.y = y;
    this.z = z;
  }
}

public class Main {

  static final int[] dx = {0, 1, 0, -1, 0, 0};
  static final int[] dy = {1, 0, -1, 0, 0, 0};
  static final int[] dz = {0, 0, 0, 0, 1, -1};

  static int[][][] board = new int[5][5][5];

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    for (int z = 0; z < 5; z++) {
      for (int y = 0; y < 5; y++) {
        for (int x = 0; x < 5; x++) {
          board[z][y][x] = in.nextInt();
        }
      }
    }

    int result = -1;

    int[] order = {0, 1, 2, 3, 4};
    do {
      for (int i = 0; i < (1 << 10); i++) {
        int[][][] maze = makeMaze(order, i);

        if (maze[0][0][0] == 0) {
          continue;
        }

        int sum = solve(maze);
        if (sum != -1 && (result == -1 || result > sum)) {
          result = sum;
        }
      }
    } while (nextPermutation(order));

    out.println(result);

    out.flush();
  }

  static int[][][] makeMaze(int[] order, int n) {
    int[][][] result = new int[5][5][5];
    for (int i = 0; i < 5; i++) {
      result[i] = board[order[i]];
      int time = n & 3;

      for (int r = 0; r < time; r++) {
        result[i] = rotate(result[i]);
      }

      n >>= 2;
    }

    return result;
  }

  static int[][] rotate(int[][] m) {
    int[][] result = new int[5][5];

    for (int y = 0; y < 5; y++) {
      for (int x = 0; x < 5; x++) {
        result[y][x] = m[4 - x][y];
      }
    }

    return result;
  }

  static int solve(int[][][] maze) {
    Queue<Pos> q = new LinkedList<>();

    int[][][] dist = new int[5][5][5];
    for (int[][] a1 : dist) {
      for (int[] a2 : a1) {
        Arrays.fill(a2, -1);
      }
    }

    q.add(new Pos(0, 0, 0));
    dist[0][0][0] = 0;

    while (!q.isEmpty()) {
      Pos cur = q.remove();

      for (int k = 0; k < 6; k++) {
        int xn = cur.x + dx[k];
        int yn = cur.y + dy[k];
        int zn = cur.z + dz[k];

        if (xn < 0 || yn < 0 || zn < 0 || xn > 4 || yn > 4 || zn > 4) {
          continue;
        }

        if (maze[zn][yn][xn] == 0 || dist[zn][yn][xn] != -1) {
          continue;
        }

        q.add(new Pos(xn, yn, zn));
        dist[zn][yn][xn] = dist[cur.z][cur.y][cur.x] + 1;
      }
    }

    return dist[4][4][4];
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