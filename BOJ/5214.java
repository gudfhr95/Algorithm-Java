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

  static int N, K, M;
  static ArrayList<Integer>[] adj = new ArrayList[101001];
  static int[] dist = new int[101001];

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    N = in.nextInt();
    K = in.nextInt();
    M = in.nextInt();

    for (int i = 1; i <= N; i++) {
      adj[i] = new ArrayList<>();
    }
    for (int i = 100001; i < 100001 + M; i++) {
      adj[i] = new ArrayList<>();
    }

    for (int y = 1; y <= M; y++) {
      for (int x = 0; x < K; x++) {
        int station = in.nextInt();

        adj[station].add(100000 + y);
        adj[100000 + y].add(station);
      }
    }

    bfs();

    out.println(dist[N]);

    out.flush();
  }

  static void bfs() {
    Queue<Integer> q = new LinkedList<>();
    Arrays.fill(dist, -1);

    q.add(1);
    dist[1] = 1;

    while (!q.isEmpty()) {
      int cur = q.remove();

      for (int next : adj[cur]) {
        if (dist[next] != -1) {
          continue;
        }

        q.add(next);
        if (next > 100000) {
          dist[next] = dist[cur];
        } else {
          dist[next] = dist[cur] + 1;
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