import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

  static final int[] dx = {0, 1, 1, 1, 0, -1, -1, -1};
  static final int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};

  static int C, N;
  static String word;
  static char[][] boggle = new char[5][5];
  static int[][][] d = new int[5][5][10];

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    C = in.nextInt();

    while (C-- > 0) {
      for (int i = 0; i < 5; i++) {
        boggle[i] = in.next().toCharArray();
      }

      N = in.nextInt();

      for (int i = 0; i < N; i++) {
        word = in.next();
        out.printf("%s ", word);
        if (hasWord()) {
          out.println("YES");
        } else {
          out.println("NO");
        }
      }
    }

    out.flush();
  }

  static boolean hasWord() {
    fill();

    for (int y = 0; y < 5; y++) {
      for (int x = 0; x < 5; x++) {
        if (solve(x, y, 0) == 1) {
          return true;
        }
      }
    }

    return false;
  }

  static void fill() {
    for (int[][] a1 : d) {
      for (int[] a2 : a1) {
        Arrays.fill(a2, -1);
      }
    }
  }

  static int solve(int x, int y, int i) {
    if (x < 0 || y < 0 || x > 4 || y > 4) {
      return 0;
    }

    if (d[y][x][i] != -1) {
      return d[y][x][i];
    }

    if (i == word.length() - 1) {
      if (word.charAt(i) != boggle[y][x]) {
        return d[y][x][i] = 0;
      } else {
        return d[y][x][i] = 1;
      }
    }

    if (word.charAt(i) != boggle[y][x]) {
      return d[y][x][i] = 0;
    }

    int result = -1;
    for (int k = 0; k < 8; k++) {
      if (solve(x + dx[k], y + dy[k], i + 1) == 1) {
        return d[y][x][i] = 1;
      }
    }

    return d[y][x][i] = 0;
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