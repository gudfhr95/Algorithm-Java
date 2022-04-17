import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

  static ArrayList<Integer> coins = new ArrayList<>();
  static int[] d = new int[10001];

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    int T = in.nextInt();

    while (T-- > 0) {
      int N = in.nextInt();

      coins.clear();
      for (int i = 0; i < N; i++) {
        coins.add(in.nextInt());
      }

      int M = in.nextInt();

      Arrays.fill(d, 0);
      d[0] = 1;
      out.println(pay(M));
    }

    out.flush();
  }

  static int pay(int m) {
    for (int coin : coins) {
      for (int i = coin; i <= m; i++) {
        d[i] += d[i - coin];
      }
    }

    return d[m];
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