import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

  static int[] students = new int[100001];
  static int[] check = new int[100001];

  static void dfs(int start) {
    int cur = start;

    while (true) {
      check[cur] = start;
      cur = students[cur];

      if (check[cur] == start) {
        while (check[cur] != -1) {
          check[cur] = -1;
          cur = students[cur];
        }
        return;
      } else if (check[cur] != 0) {
        return;
      }
    }
  }

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    int T = in.nextInt();
    while (T-- > 0) {
      int n = in.nextInt();

      for (int i = 1; i <= n; i++) {
        students[i] = in.nextInt();
      }

      Arrays.fill(check, 0);
      for (int i = 1; i <= n; i++) {
        if (check[i] == 0) {
          dfs(i);
        }
      }

      int result = 0;
      for (int i = 1; i <= n; i++) {
        if (check[i] != -1) {
          result += 1;
        }
      }

      out.println(result);
    }

    out.flush();
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