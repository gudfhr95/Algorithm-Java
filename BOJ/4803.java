import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

  static int n, m;
  static ArrayList<Integer>[] adj = new ArrayList[501];
  static boolean[] visited = new boolean[501];

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    for (int t = 1; ; t++) {
      int n = in.nextInt();
      int m = in.nextInt();

      if (n == 0 && m == 0) {
        break;
      }

      Arrays.fill(adj, null);
      Arrays.fill(visited, false);

      for (int i = 1; i <= n; i++) {
        adj[i] = new ArrayList<>();
      }

      for (int i = 0; i < m; i++) {
        int a = in.nextInt();
        int b = in.nextInt();

        adj[a].add(b);
        adj[b].add(a);
      }

      int result = 0;
      for (int i = 1; i <= n; i++) {
        if (!visited[i] && isTree(i, 0)) {
          result += 1;
        }
      }

      out.printf("Case %d: ", t);
      if (result == 0) {
        out.printf("No trees.\n");
      } else if (result == 1) {
        out.printf("There is one tree.\n");
      } else {
        out.printf("A forest of %d trees.\n", result);
      }
    }

    out.flush();
  }

  static boolean isTree(int n, int from) {
    visited[n] = true;

    for (int child : adj[n]) {
      if (child == from) {
        continue;
      }

      if (visited[child] || !isTree(child, n)) {
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