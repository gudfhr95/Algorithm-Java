import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Main {

  static final int scores[] = {
      0, 2, 4, 6, 8, 10, 12, 14, 16, 18, 20,
      22, 24, 26, 28, 30, 32, 34, 36, 38, 40, 13,
      16, 19, 25, 30, 35, 22, 24, 28, 27, 26, 0
  };
  static int next[] = new int[32];
  static int special[] = new int[32];

  static int[] dices = new int[10];
  static int[] pos = new int[4];
  static int result = 0;

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    special[5] = 21;
    special[10] = 27;
    special[15] = 29;

    for (int i = 0; i < 32; i++) {
      next[i] = i + 1;
    }
    next[20] = 32;
    next[26] = 20;
    next[28] = 24;
    next[31] = 24;

    for (int i = 0; i < 10; i++) {
      dices[i] = in.nextInt();
    }

    simulate(0, 0);

    out.println(result);

    out.flush();
  }

  static void simulate(int index, int score) {
    if (index == 10) {
      result = Math.max(result, score);
      return;
    }

    for (int p = 0; p < 4; p++) {
      int position = pos[p];

      if (position == 32) {
        continue;
      }

      position = move(position, dices[index]);

      if (position != 32 && !canMove(position)) {
        continue;
      }

      int temp = pos[p];
      pos[p] = position;
      simulate(index + 1, score + scores[position]);
      pos[p] = temp;
    }
  }

  static int move(int p, int d) {
    for (int i = 0; i < d; i++) {
      if (p == 32) {
        break;
      }

      if (i == 0 && special[p] != 0) {
        p = special[p];
      } else {
        p = next[p];
      }
    }

    return p;
  }

  static boolean canMove(int p) {
    for (int i = 0; i < 4; i++) {
      if (pos[i] == p) {
        return false;
      }
    }

    return true;
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