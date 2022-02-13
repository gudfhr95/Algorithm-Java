import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

  static char[][] gears = new char[4][8];
  static boolean[] rotated = new boolean[4];

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    for (int i = 0; i < 4; i++) {
      gears[i] = in.next().toCharArray();
    }

    int K = in.nextInt();

    for (int k = 0; k < K; k++) {
      int n = in.nextInt();
      int d = in.nextInt();

      Arrays.fill(rotated, false);
      rotate(n - 1, d);
    }

    int result = 0;
    int score = 1;
    for (int i = 0; i < 4; i++) {
      if (gears[i][0] == '1') {
        result += score;
      }
      score *= 2;
    }

    out.println(result);

    out.flush();
  }

  static void rotate(int n, int d) {
    if (rotated[n]) {
      return;
    }

    boolean left = false, right = false;
    if (n - 1 >= 0) {
      left = (gears[n][6] != gears[n - 1][2]);
    }
    if (n + 1 <= 3) {
      right = (gears[n][2] != gears[n + 1][6]);
    }

    if (d == 1) {
      rotateClockwise(n);
    } else {
      rotateCounterClockwise(n);
    }
    rotated[n] = true;

    if (left) {
      rotate(n - 1, d * -1);
    }

    if (right) {
      rotate(n + 1, d * -1);
    }
  }

  static void rotateClockwise(int n) {
    char[] result = new char[8];

    for (int i = 0; i < 7; i++) {
      result[i + 1] = gears[n][i];
    }
    result[0] = gears[n][7];

    gears[n] = result;
  }

  static void rotateCounterClockwise(int n) {
    char[] result = new char[8];

    for (int i = 0; i < 7; i++) {
      result[i] = gears[n][i + 1];
    }
    result[7] = gears[n][0];

    gears[n] = result;
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