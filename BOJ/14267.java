import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

  static int n, m;
  static int[] bosses = new int[100001];
  static ArrayList<Integer>[] adj = new ArrayList[100001];
  static int[] score = new int[100001];

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    n = in.nextInt();
    m = in.nextInt();

    for (int i = 1; i <= n; i++) {
      adj[i] = new ArrayList<>();
    }

    for (int i = 1; i <= n; i++) {
      bosses[i] = in.nextInt();

      if (bosses[i] == -1) {
        continue;
      }

      adj[i].add(bosses[i]);
      adj[bosses[i]].add(i);
    }

    for (int i = 0; i < m; i++) {
      int e = in.nextInt();
      int w = in.nextInt();

      score[e] += w;
    }

    dfs(1);

    for (int i = 1; i <= n; i++) {
      out.printf("%d ", score[i]);
    }

    out.flush();
  }

  static void dfs(int n) {
    for (int next : adj[n]) {
      if (next == -1 || next == bosses[n]) {
        continue;
      }

      score[next] += score[n];
      dfs(next);
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