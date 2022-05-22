import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

  static int N;
  static boolean[][] friend = new boolean[51][51];
  static int[] score = new int[51];

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    N = in.nextInt();
    while (true) {
      int a = in.nextInt();
      int b = in.nextInt();

      if (a == -1 && b == -1) {
        break;
      }

      friend[a][b] = friend[b][a] = true;
    }

    for (int i = 1; i <= N; i++) {
      score[i] = bfs(i);
    }

    int min = Integer.MAX_VALUE;
    for (int i = 1; i <= N; i++) {
      min = Math.min(min, score[i]);
    }

    int count = 0;
    for (int i = 1; i <= N; i++) {
      if (score[i] == min) {
        count += 1;
      }
    }

    out.printf("%d %d\n", min, count);

    for (int i = 1; i <= N; i++) {
      if (score[i] == min) {
        out.printf("%d ", i);
      }
    }

    out.flush();
  }

  static int bfs(int n) {
    Queue<Integer> q = new LinkedList<>();
    int[] dist = new int[N + 1];
    Arrays.fill(dist, -1);

    q.add(n);
    dist[n] = 0;

    while (!q.isEmpty()) {
      int cur = q.remove();

      for (int i = 1; i <= N; i++) {
        if (!friend[cur][i]) {
          continue;
        }

        if (dist[i] != -1) {
          continue;
        }

        q.add(i);
        dist[i] = dist[cur] + 1;
      }
    }

    int result = Integer.MIN_VALUE;
    for (int i = 1; i <= N; i++) {
      result = Math.max(result, dist[i]);
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