import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

  static InputReader in = new InputReader(System.in);
  static PrintWriter out = new PrintWriter(System.out);

  static int C, N, W;
  static String[] name = new String[101];
  static int[] volume = new int[101];
  static int[] necessity = new int[101];
  static int[][] d = new int[101][1001];
  static boolean[][] picked = new boolean[101][1001];

  public static void main(String[] args) {
    C = in.nextInt();

    while (C-- > 0) {
      N = in.nextInt();
      W = in.nextInt();

      for (int i = 1; i <= N; i++) {
        name[i] = in.next();
        volume[i] = in.nextInt();
        necessity[i] = in.nextInt();
      }

      for (int[] d1 : d) {
        Arrays.fill(d1, 0);
      }

      for (boolean[] p1 : picked) {
        Arrays.fill(p1, false);
      }

      pick();

      out.printf("%d %d\n", d[N][W], count());

      print();
    }

    out.flush();
  }

  static void pick() {
    for (int i = 1; i <= N; i++) {
      for (int j = 0; j <= W; j++) {
        int v = j - volume[i];
        if (v >= 0 && necessity[i] + d[i - 1][v] > d[i][j]) {
          d[i][j] = necessity[i] + d[i - 1][v];
          picked[i][j] = true;
        }

        if (d[i - 1][j] > d[i][j]) {
          d[i][j] = d[i - 1][j];
          picked[i][j] = false;
        }
      }
    }
  }

  static int count() {
    int cur = N;
    int weight = W;

    int result = 0;
    while (weight > 0 && cur >= 0) {
      if (picked[cur][weight]) {
        weight -= volume[cur];
        result += 1;
      }

      cur -= 1;
    }

    return result;
  }

  static void print() {
    int cur = N;
    int weight = W;

    Stack<String> result = new Stack<>();
    while (weight > 0 && cur >= 0) {
      if (picked[cur][weight]) {
        result.add(name[cur]);
        weight -= volume[cur];
      }

      cur -= 1;
    }

    while (!result.isEmpty()) {
      out.println(result.pop());
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