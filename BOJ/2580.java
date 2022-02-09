import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Main {

  static int[][] sudoku = new int[9][9];
  static boolean[][] row = new boolean[9][10];
  static boolean[][] col = new boolean[9][10];
  static boolean[][] square = new boolean[9][10];

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    for (int y = 0; y < 9; y++) {
      for (int x = 0; x < 9; x++) {
        sudoku[y][x] = in.nextInt();

        if (sudoku[y][x] != 0) {
          row[y][sudoku[y][x]] = true;
          col[x][sudoku[y][x]] = true;
          square[getSquare(x, y)][sudoku[y][x]] = true;
        }
      }
    }

    solve(0);

    for (int y = 0; y < 9; y++) {
      for (int x = 0; x < 9; x++) {
        out.printf("%d ", sudoku[y][x]);
      }
      out.println();
    }

    out.flush();
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

    for (int i = 1; i <= 9; i++) {
      if (row[y][i] || col[x][i] || square[getSquare(x, y)][i]) {
        continue;
      }

      sudoku[y][x] = i;
      row[y][i] = true;
      col[x][i] = true;
      square[getSquare(x, y)][i] = true;

      if (solve(n + 1)) {
        return true;
      }

      sudoku[y][x] = 0;
      row[y][i] = false;
      col[x][i] = false;
      square[getSquare(x, y)][i] = false;
    }

    return false;
  }

  static int getSquare(int x, int y) {
    return (x / 3) + (y / 3) * 3;
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