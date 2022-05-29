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
  static ArrayList<Integer>[] adj = new ArrayList[101];
  static int[] indegree = new int[101];
  static int[] outdegree = new int[101];
  static int[] result = new int[101];

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    N = in.nextInt();
    M = in.nextInt();

    for (int i = 1; i <= N; i++) {
      adj[i] = new ArrayList<>();
    }

    for (int i = 0; i < M; i++) {
      int X = in.nextInt();
      int Y = in.nextInt();
      int K = in.nextInt();

      for (int j = 0; j < K; j++) {
        adj[X].add(Y);
        indegree[Y] += 1;
        outdegree[X] += 1;
      }
    }

    topologicalSort();

    for (int i = 1; i < N; i++) {
      if (outdegree[i] == 0) {
        out.printf("%d %d\n", i, result[i]);
      }
    }

    out.flush();
  }

  static void topologicalSort() {
    Queue<Integer> q = new LinkedList<>();

    q.add(N);
    result[N] = 1;

    while (!q.isEmpty()) {
      int cur = q.remove();

      for (int next : adj[cur]) {
        indegree[next] -= 1;
        result[next] += result[cur];

        if (indegree[next] == 0) {
          q.add(next);
        }
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