import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

  static int N, R, Q;
  static ArrayList<Integer>[] adj = new ArrayList[100001];
  static int[] d = new int[100001];

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    N = in.nextInt();
    R = in.nextInt();
    Q = in.nextInt();

    for (int i = 1; i <= N; i++) {
      adj[i] = new ArrayList<>();
    }

    for (int i = 0; i < N - 1; i++) {
      int U = in.nextInt();
      int V = in.nextInt();

      adj[U].add(V);
      adj[V].add(U);
    }

    getNodes(R, -1);

    for (int i = 0; i < Q; i++) {
      int U = in.nextInt();

      out.println(d[U]);
    }

    out.flush();
  }

  static int getNodes(int u, int p) {
    if (d[u] != 0) {
      return d[u];
    }

    if (adj[u].size() == 1 && adj[u].get(0) == p) {
      return d[u] = 1;
    }

    int result = 1;
    for (int child : adj[u]) {
      if (child == p) {
        continue;
      }

      result += getNodes(child, u);
    }

    return d[u] = result;
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