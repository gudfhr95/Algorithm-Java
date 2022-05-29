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

  static int T, N, K;
  static int[] D = new int[1001];
  static ArrayList<Integer>[] adj = new ArrayList[1001];
  static int[] indegree = new int[1001];
  static int[] time = new int[1001];

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    T = in.nextInt();
    while (T-- > 0) {
      N = in.nextInt();
      K = in.nextInt();

      for (int i = 1; i <= N; i++) {
        D[i] = in.nextInt();
        adj[i] = new ArrayList<>();
        indegree[i] = 0;
        time[i] = 0;
      }

      for (int i = 0; i < K; i++) {
        int X = in.nextInt();
        int Y = in.nextInt();

        adj[X].add(Y);
        indegree[Y] += 1;
      }

      topologicalSort();

      out.println(time[in.nextInt()]);
    }

    out.flush();
  }

  static void topologicalSort() {
    Queue<Integer> q = new LinkedList<>();

    for (int i = 1; i <= N; i++) {
      if (indegree[i] == 0) {
        q.add(i);

        time[i] = D[i];
      }
    }

    while (!q.isEmpty()) {
      int cur = q.remove();

      for (int next : adj[cur]) {
        indegree[next] -= 1;
        time[next] = Math.max(time[next], time[cur]);

        if (indegree[next] == 0) {
          q.add(next);

          time[next] += D[next];
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