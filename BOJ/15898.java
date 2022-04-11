import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

  static int N;
  static int[][][][][] ingredients = new int[10][4][4][4][2];
  static int[][][] pot = new int[5][5][2];
  static int answer = 0;

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    N = in.nextInt();
    for (int i = 0; i < N; i++) {
      for (int y = 0; y < 4; y++) {
        for (int x = 0; x < 4; x++) {
          ingredients[i][0][y][x][0] = in.nextInt();
        }
      }

      for (int y = 0; y < 4; y++) {
        for (int x = 0; x < 4; x++) {
          ingredients[i][0][y][x][1] = in.next().charAt(0);
        }
      }
    }

    int[] c = new int[N];
    c[N - 1] = c[N - 2] = c[N - 3] = 1;
    for (int i = 0; i < N; i++) {
      int[][][] temp = ingredients[i][0];
      for (int r = 1; r < 4; r++) {
        temp = rotate(temp);
        ingredients[i][r] = temp;
      }
    }

    int[] a = new int[3];
    boolean[] used = new boolean[N];

    Arrays.fill(a, -1);
    simulate(0, a, used);

    out.println(answer);

    out.flush();
  }

  static void simulate(int n, int[] a, boolean[] used) {
    if (n == 3) {
      calculate(a);
      return;
    }

    for (int i = 0; i < N; i++) {
      if (used[i]) {
        continue;
      }

      a[n] = i;
      used[i] = true;

      simulate(n + 1, a, used);

      a[n] = -1;
      used[i] = false;
    }
  }

  static void calculate(int[] a) {
    for (int r = 0; r < 1 << (2 * 3); r++) {
      for (int p = 0; p < 1 << (2 * 3); p++) {
        mix(a, r, p);
        answer = Math.max(answer, getQuality());
      }
    }
  }

  static void mix(int[] a, int r, int p) {
    for (int y = 0; y < 5; y++) {
      for (int x = 0; x < 5; x++) {
        pot[y][x][0] = 0;
        pot[y][x][1] = 'W';
      }
    }

    for (int i = 0; i < 3; i++) {
      int[][][] cur = ingredients[a[i]][r & 3];

      addIngredient(cur, p & 3);

      r >>= 2;
      p >>= 2;
    }
  }

  static int getQuality() {
    int result = 0;
    for (int y = 0; y < 5; y++) {
      for (int x = 0; x < 5; x++) {
        switch (pot[y][x][1]) {
          case 'R':
            result += pot[y][x][0] * 7;
            break;
          case 'B':
            result += pot[y][x][0] * 5;
            break;
          case 'G':
            result += pot[y][x][0] * 3;
            break;
          case 'Y':
            result += pot[y][x][0] * 2;
            break;
        }
      }
    }

    return result;
  }

  static void addIngredient(int[][][] b, int p) {
    int c = p % 2;
    int r = p / 2;

    for (int y = 0; y < 4; y++) {
      for (int x = 0; x < 4; x++) {
        int xa = x + c;
        int ya = y + r;

        pot[ya][xa][0] += b[y][x][0];
        if (pot[ya][xa][0] < 0) {
          pot[ya][xa][0] = 0;
        }
        if (pot[ya][xa][0] > 9) {
          pot[ya][xa][0] = 9;
        }

        if (b[y][x][1] != 'W') {
          pot[ya][xa][1] = b[y][x][1];
        }
      }
    }
  }

  static int[][][] rotate(int[][][] a) {
    int[][][] result = new int[4][4][2];

    for (int y = 0; y < 4; y++) {
      for (int x = 0; x < 4; x++) {
        result[y][x] = a[x][3 - y];
      }
    }

    return result;
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