import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

  static int N, M;
  static ArrayList<Integer>[] adj = new ArrayList[100001];
  static boolean[] visited = new boolean[100001];
  int result = 0;

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    N = in.nextInt();
    M = in.nextInt();

    for (int i = 1; i <= N; i++) {
      adj[i] = new ArrayList<>();
    }

    for (int i = 0; i < M; i++) {
      int u = in.nextInt();
      int v = in.nextInt();

      adj[u].add(v);
      adj[v].add(u);
    }

    int used = 0;
    int treeCount = 0;
    for (int i = 1; i <= N; i++) {
      if (!visited[i]) {
        used += mst(i);
        treeCount += 1;
      }
    }

    int result = 0;
    for (int i = 1; i <= N; i++) {
      result += adj[i].size();
    }
    result = (result / 2) - used + treeCount - 1;

    out.println(result);

    out.flush();
  }

  static int mst(int start) {
    Queue<Integer> q = new LinkedList<>();

    q.add(start);
    visited[start] = true;

    int result = 0;
    while (!q.isEmpty()) {
      int cur = q.remove();

      for (int next : adj[cur]) {
        if (visited[next]) {
          continue;
        }

        q.add(next);
        visited[next] = true;
        result += 1;
      }
    }

    return result;
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
