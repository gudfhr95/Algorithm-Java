import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

  static int N, M;
  static int[] budgets = new int[10000];

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    N = in.nextInt();
    for (int i = 0; i < N; i++) {
      budgets[i] = in.nextInt();
    }
    M = in.nextInt();

    Arrays.sort(budgets, 0, N);

    out.println(execute());

    out.flush();
  }

  static int execute() {
    int l = 1;
    int r = budgets[N - 1];

    while (l <= r) {
      int mid = (l + r) / 2;

      if (canExecute(mid)) {
        l = mid + 1;
      } else {
        r = mid - 1;
      }
    }

    return r;
  }

  static boolean canExecute(int money) {
    int totalBudget = 0;
    for (int budget : budgets) {
      totalBudget += Math.min(budget, money);
    }

    return totalBudget <= M;
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
