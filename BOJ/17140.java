import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

class Pair implements Comparable<Pair> {

  int n, c;

  public Pair(int n, int c) {
    this.n = n;
    this.c = c;
  }

  @Override
  public int compareTo(Pair o) {
    if (c == o.c) {
      return n - o.n;
    }

    return c - o.c;
  }
}

public class Main {

  static int r, c, k, rowSize, colSize;
  static int[][] A = new int[100][100];

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    r = in.nextInt();
    c = in.nextInt();
    k = in.nextInt();

    rowSize = colSize = 3;
    for (int y = 0; y < 3; y++) {
      for (int x = 0; x < 3; x++) {
        A[y][x] = in.nextInt();
      }
    }

    out.println(simulate());

    out.flush();
  }

  static int simulate() {
    int t = 0;
    while (t <= 100) {
      if (A[r - 1][c - 1] == k) {
        return t;
      }

      if (rowSize >= colSize) {
        ROperation();
      } else {
        COperation();
      }

      t += 1;
    }

    return -1;
  }

  static void ROperation() {
    ArrayList<Pair>[] rowPairs = new ArrayList[rowSize];
    for (int y = 0; y < rowSize; y++) {
      rowPairs[y] = new ArrayList<>();

      int[] count = new int[101];
      for (int x = 0; x < colSize; x++) {
        if (A[y][x] == 0) {
          continue;
        }

        count[A[y][x]] += 1;
      }

      for (int i = 0; i <= 100; i++) {
        if (count[i] != 0) {
          rowPairs[y].add(new Pair(i, count[i]));
        }
      }
    }

    for (int y = 0; y < rowSize; y++) {
      colSize = Math.max(colSize, rowPairs[y].size() * 2);
    }
    colSize = Math.min(colSize, 100);

    for (int y = 0; y < rowSize; y++) {
      Collections.sort(rowPairs[y]);

      int x = 0;
      for (Pair p : rowPairs[y]) {
        A[y][x++] = p.n;
        A[y][x++] = p.c;

        if (x >= colSize) {
          break;
        }
      }

      while (x < colSize) {
        A[y][x++] = 0;
      }
    }
  }

  static void COperation() {
    ArrayList<Pair>[] colPairs = new ArrayList[colSize];
    for (int x = 0; x < colSize; x++) {
      colPairs[x] = new ArrayList<>();

      int[] count = new int[101];
      for (int y = 0; y < rowSize; y++) {
        if (A[y][x] == 0) {
          continue;
        }

        count[A[y][x]] += 1;
      }

      for (int i = 0; i <= 100; i++) {
        if (count[i] != 0) {
          colPairs[x].add(new Pair(i, count[i]));
        }
      }
    }

    for (int x = 0; x < colSize; x++) {
      rowSize = Math.max(rowSize, colPairs[x].size() * 2);
    }
    rowSize = Math.min(rowSize, 100);

    for (int x = 0; x < colSize; x++) {
      Collections.sort(colPairs[x]);

      int y = 0;
      for (Pair p : colPairs[x]) {
        A[y++][x] = p.n;
        A[y++][x] = p.c;

        if (y >= rowSize) {
          break;
        }
      }

      while (y < rowSize) {
        A[y++][x] = 0;
      }
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