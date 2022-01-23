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

  static int[] dist = new int[20000];
  static int[] from = new int[20000];
  static int[] how = new int[20000];

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    int T = in.nextInt();

    while (T-- > 0) {
      int N = in.nextInt();

      Arrays.fill(dist, -1);
      Arrays.fill(from, -1);
      Arrays.fill(how, -1);

      bfs(N);

      out.println(getResult());

      out.flush();
    }
  }

  static void bfs(int n) {
    Queue<Integer> q = new LinkedList<>();

    q.add(1 % n);
    dist[1 % n] = 0;
    how[1 % n] = 1;

    while (!q.isEmpty()) {
      int cur = q.remove();

      for (int k = 0; k <= 1; k++) {
        int next = (cur * 10 + k) % n;
        int dn = dist[cur] + 1;

        if (dist[next] != -1 || dn > 100) {
          continue;
        }

        q.add(next);
        dist[next] = dn;
        from[next] = cur;
        how[next] = k;
      }
    }
  }

  static String getResult() {
    if (dist[0] == -1) {
      return "BRAK";
    }

    StringBuilder result = new StringBuilder();
    for (int i = 0; i != -1; i = from[i]) {
      result.append(how[i]);
    }

    return result.reverse().toString();
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