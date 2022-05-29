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
  static ArrayList<Integer>[] adj = new ArrayList[1001];
  static int[] indegree = new int[1001];

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    N = in.nextInt();
    M = in.nextInt();

    for (int i = 1; i <= N; i++) {
      adj[i] = new ArrayList<>();
    }

    for (int i = 0; i < M; i++) {
      int n = in.nextInt();

      int prev = in.nextInt();
      for (int j = 1; j < n; j++) {
        int cur = in.nextInt();

        adj[prev].add(cur);
        indegree[cur] += 1;

        prev = cur;
      }
    }

    ArrayList<Integer> result = topologicalSort();

    if (result.size() != N) {
      out.println(0);
    } else {
      for (int r : result) {
        out.printf("%d ", r);
      }
    }

    out.flush();
  }

  static ArrayList<Integer> topologicalSort() {
    ArrayList<Integer> result = new ArrayList<>();

    Queue<Integer> q = new LinkedList<>();

    for (int i = 1; i <= N; i++) {
      if (indegree[i] == 0) {
        q.add(i);
      }
    }

    while (!q.isEmpty()) {
      int cur = q.remove();
      result.add(cur);

      for (int next : adj[cur]) {
        indegree[next] -= 1;

        if (indegree[next] == 0) {
          q.add(next);
        }
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