import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

  static final int UNWATCHED = 0, WATCHED = 1, INSTALLED = 2;

  static int G, result;
  static ArrayList<Integer>[] adj = new ArrayList[1000];
  static boolean[] visited = new boolean[1000];

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    int C = in.nextInt();

    while (C-- > 0) {
      G = in.nextInt();
      int H = in.nextInt();

      for (int i = 0; i < G; i++) {
        adj[i] = new ArrayList<>();
      }

      for (int i = 0; i < H; i++) {
        int u = in.nextInt();
        int v = in.nextInt();

        adj[u].add(v);
        adj[v].add(u);
      }

      installCamera();

      out.println(result);
    }

    out.flush();
  }

  static void installCamera() {
    result = 0;
    Arrays.fill(visited, false);

    for (int i = 0; i < G; i++) {
      if (!visited[i] && dfs(i) == UNWATCHED) {
        result += 1;
      }
    }
  }

  static int dfs(int here) {
    visited[here] = true;

    int[] children = {0, 0, 0};
    for (int there : adj[here]) {
      if (!visited[there]) {
        children[dfs(there)] += 1;
      }
    }

    if (children[UNWATCHED] > 0) {
      result += 1;
      return INSTALLED;
    }

    if (children[INSTALLED] > 0) {
      return WATCHED;
    }

    return UNWATCHED;
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