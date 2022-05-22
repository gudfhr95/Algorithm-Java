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

  static int N, M;
  static ArrayList<Integer>[] adj = new ArrayList[20001];
  static int[] dist = new int[20001];

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    N = in.nextInt();
    M = in.nextInt();

    for (int i = 1; i <= N; i++) {
      adj[i] = new ArrayList<>();
    }

    for (int i = 0; i < M; i++) {
      int A = in.nextInt();
      int B = in.nextInt();

      adj[A].add(B);
      adj[B].add(A);
    }

    bfs();

    int result = -1;
    int max = Integer.MIN_VALUE;
    for (int i = 1; i <= N; i++) {
      if (dist[i] > max) {
        max = dist[i];
        result = i;
      }
    }

    out.printf("%d %d ", result, max);

    int count = 0;
    for (int i = 1; i <= N; i++) {
      if (dist[i] == max) {
        count += 1;
      }
    }

    out.printf("%d", count);

    out.flush();
  }

  static void bfs() {
    Queue<Integer> q = new LinkedList<>();
    Arrays.fill(dist, -1);

    q.add(1);
    dist[1] = 0;

    while (!q.isEmpty()) {
      int cur = q.remove();

      for (int next : adj[cur]) {
        if (dist[next] != -1) {
          continue;
        }

        q.add(next);
        dist[next] = dist[cur] + 1;
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