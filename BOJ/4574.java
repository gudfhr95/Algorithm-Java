import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

  static InputReader in = new InputReader(System.in);
  static PrintWriter out = new PrintWriter(System.out);

  static int[] dx = {0, 1};
  static int[] dy = {1, 0};

  static int[][] sudoku = new int[9][9];
  static boolean[][] domino = new boolean[10][10];
  static boolean[][] row = new boolean[9][10];
  static boolean[][] col = new boolean[9][10];
  static boolean[][] square = new boolean[9][10];

  public static void main(String[] args) {
    for (int t = 1; ; t++) {
      int N = in.nextInt();

      if (N == 0) {
        break;
      }

      makeSudoku(N);

      solve(0);

      out.printf("Puzzle %d\n", t);

      for (int y = 0; y < 9; y++) {
        for (int x = 0; x < 9; x++) {
          out.print(sudoku[y][x]);
        }
        out.println();
      }
    }

    out.flush();
  }

  static void makeSudoku(int n) {
    for (int[] a1 : sudoku) {
      Arrays.fill(a1, 0);
    }

    for (boolean[] a1 : domino) {
      Arrays.fill(a1, true);
    }

    for (boolean[] a1 : row) {
      Arrays.fill(a1, false);
    }

    for (boolean[] a1 : col) {
      Arrays.fill(a1, false);
    }

    for (boolean[] a1 : square) {
      Arrays.fill(a1, false);
    }

    for (int i = 0; i < n; i++) {
      int U = in.nextInt();
      String LU = in.next();
      int V = in.nextInt();
      String LV = in.next();

      int xu = LU.charAt(1) - '0' - 1;
      int yu = LU.charAt(0) - 'A';
      int xv = LV.charAt(1) - '0' - 1;
      int yv = LV.charAt(0) - 'A';

      set(xu, yu, U);
      set(xv, yv, V);

      domino[U][V] = domino[V][U] = false;
    }

    for (int i = 1; i <= 9; i++) {
      String LW = in.next();

      int xw = LW.charAt(1) - '0' - 1;
      int yw = LW.charAt(0) - 'A';

      set(xw, yw, i);
    }
  }

  static boolean solve(int n) {
    if (n == 81) {
      return true;
    }

    int x = n % 9;
    int y = n / 9;

    if (sudoku[y][x] != 0) {
      return solve(n + 1);
    }

    for (int k = 0; k < 2; k++) {
      int xn = x + dx[k];
      int yn = y + dy[k];

      if (xn >= 9 || yn >= 9) {
        continue;
      }

      if (sudoku[yn][xn] != 0) {
        continue;
      }

      for (int i = 1; i <= 9; i++) {
        for (int j = 1; j <= 9; j++) {
          if (i == j) {
            continue;
          }

          if (!domino[i][j]) {
            continue;
          }

          if (canSet(x, y, i) && canSet(xn, yn, j)) {
            set(x, y, i);
            set(xn, yn, j);
            domino[i][j] = domino[j][i] = false;

            if (solve(n + 1)) {
              return true;
            }

            unset(x, y);
            unset(xn, yn);
            domino[i][j] = domino[j][i] = true;
          }
        }
      }
    }

    return false;
  }

  static void set(int x, int y, int v) {
    sudoku[y][x] = v;
    row[y][v] = col[x][v] = square[getSquare(x, y)][v] = true;
  }

  static void unset(int x, int y) {
    int v = sudoku[y][x];

    row[y][v] = col[x][v] = square[getSquare(x, y)][v] = false;
    sudoku[y][x] = 0;
  }

  static boolean canSet(int x, int y, int v) {
    return !row[y][v] && !col[x][v] && !square[getSquare(x, y)][v];
  }

  static int getSquare(int x, int y) {
    return (y / 3) * 3 + (x / 3);
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