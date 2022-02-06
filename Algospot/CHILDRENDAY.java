import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    int T = in.nextInt();

    while (T-- > 0) {
      char[] D = in.next().toCharArray();
      int N = in.nextInt();
      int M = in.nextInt();

      out.println(gift(D, N, M));
    }

    out.flush();
  }

  static String gift(char[] d, int n, int m) {
    Arrays.sort(d);

    int[] parent = new int[n * 2];
    int[] choice = new int[n * 2];

    Arrays.fill(parent, -1);
    Arrays.fill(choice, -1);

    Queue<Integer> q = new LinkedList<>();

    q.add(0);
    parent[0] = 0;

    while (!q.isEmpty()) {
      int cur = q.remove();

      for (int i = 0; i < d.length; i++) {
        int next = getNext(cur, d[i] - '0', n);

        if (parent[next] != -1) {
          continue;
        }

        q.add(next);
        parent[next] = cur;
        choice[next] = d[i] - '0';
      }
    }

    if (parent[n + m] == -1) {
      return "IMPOSSIBLE";
    }

    StringBuilder result = new StringBuilder();
    int cur = n + m;
    while (parent[cur] != cur) {
      result.append(choice[cur]);
      cur = parent[cur];
    }

    return result.reverse().toString();
  }

  static int getNext(int cur, int edge, int mod) {
    int next = cur * 10 + edge;
    if (next >= mod) {
      return mod + (next % mod);
    } else {
      return next % mod;
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