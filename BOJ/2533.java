import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

  static int N;
  static ArrayList<Integer>[] adj = new ArrayList[1000001];
  static int[][] cache = new int[1000001][2];

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    N = in.nextInt();

    for (int i = 1; i <= N; i++) {
      adj[i] = new ArrayList<>();
    }

    for (int i = 0; i < N - 1; i++) {
      int u = in.nextInt();
      int v = in.nextInt();

      adj[u].add(v);
      adj[v].add(u);
    }

    makeTree();

    for (int[] a1 : cache) {
      Arrays.fill(a1, -1);
    }

    out.println(Math.min(dfs(1, 0), dfs(1, 1)));

    out.flush();
  }

  static void makeTree() {
    ArrayList<Integer>[] result = new ArrayList[N + 1];
    for (int i = 1; i <= N; i++) {
      result[i] = new ArrayList<>();
    }

    Queue<Integer> q = new LinkedList<>();
    boolean[] visited = new boolean[N + 1];

    q.add(1);
    visited[1] = true;

    while (!q.isEmpty()) {
      int cur = q.remove();

      for (int next : adj[cur]) {
        if (visited[next]) {
          continue;
        }

        q.add(next);
        visited[next] = true;
        result[cur].add(next);
      }
    }

    adj = result;
  }

  static int dfs(int n, int isEarlyAdapter) {
    if (cache[n][isEarlyAdapter] != -1) {
      return cache[n][isEarlyAdapter];
    }

    if (isEarlyAdapter == 0) {
      cache[n][isEarlyAdapter] = 0;
      for (int next : adj[n]) {
        cache[n][isEarlyAdapter] += dfs(next, 1);
      }
    } else {
      cache[n][isEarlyAdapter] = 1;
      for (int next : adj[n]) {
        cache[n][isEarlyAdapter] += Math.min(dfs(next, 0), dfs(next, 1));
      }
    }

    return cache[n][isEarlyAdapter];
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