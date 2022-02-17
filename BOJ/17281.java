import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

  static int N;
  static int[][] score = new int[51][10];

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    N = in.nextInt();
    for (int y = 1; y <= N; y++) {
      for (int x = 1; x <= 9; x++) {
        score[y][x] = in.nextInt();
      }
    }

    int[] players = new int[8];
    for (int i = 0; i < 8; i++) {
      players[i] = i + 2;
    }

    int result = 0;
    do {
      result = Math.max(result, play(makeOrder(players)));
    } while (nextPermutation(players));

    out.println(result);

    out.flush();
  }

  static int[] makeOrder(int[] players) {
    int[] result = new int[9];

    int index = 0;
    for (int i = 0; i < 3; i++) {
      result[index++] = players[i];
    }
    result[index++] = 1;
    for (int i = 3; i < players.length; i++) {
      result[index++] = players[i];
    }

    return result;
  }

  static int play(int[] order) {
    int result = 0;

    boolean[] base = new boolean[4];
    int inning = 1;
    int out = 0;
    int index = 0;

    while (inning <= N) {
      int cur = order[index % 9];
      index += 1;

      base[0] = true;

      if (score[inning][cur] == 0) {
        out += 1;
      } else {
        result += move(base, score[inning][cur]);
      }

      if (out >= 3) {
        inning += 1;

        Arrays.fill(base, false);
        out = 0;
      }
    }

    return result;
  }

  static int move(boolean[] base, int n) {
    int result = 0;
    for (int i = 3; i >= 0; i--) {
      if (!base[i]) {
        continue;
      }

      if (i + n > 3) {
        base[i] = false;
        result += 1;
      } else {
        base[i + n] = true;
        base[i] = false;
      }
    }

    return result;
  }

  static boolean nextPermutation(int[] a) {
    int i = a.length - 1;

    while (i > 0 && a[i] <= a[i - 1]) {
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