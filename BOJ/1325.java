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
  static ArrayList<Integer>[] adj = new ArrayList[10001];
  static int[] indegree = new int[10001];

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    N = in.nextInt();
    M = in.nextInt();

    for (int i = 1; i <= N; i++) {
      adj[i] = new ArrayList<>();
    }

    for (int i = 0; i < M; i++) {
      int a = in.nextInt();
      int b = in.nextInt();

      adj[a].add(b);
    }

    for (int i = 1; i <= N; i++) {
      bfs(i);
    }

    int max = Integer.MIN_VALUE;
    for (int i = 1; i <= N; i++) {
      max = Math.max(max, indegree[i]);
    }

    for (int i = 1; i <= N; i++) {
      if (indegree[i] == max) {
        out.printf("%d ", i);
      }
    }

    out.flush();
  }

  static void bfs(int n) {
    Queue<Integer> q = new LinkedList<>();
    boolean[] visited = new boolean[N + 1];

    q.add(n);
    visited[n] = true;

    while (!q.isEmpty()) {
      int cur = q.remove();
      indegree[cur] += 1;

      for (int next : adj[cur]) {
        if (visited[next]) {
          continue;
        }

        q.add(next);
        visited[next] = true;
      }
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