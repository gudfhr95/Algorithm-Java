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
  static int[] populations = new int[11];
  static boolean[][] adj = new boolean[11][11];
  static int[] district = new int[11];
  static boolean[] visited = new boolean[11];

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    N = in.nextInt();

    for (int i = 1; i <= N; i++) {
      populations[i] = in.nextInt();
    }

    for (int i = 1; i <= N; i++) {
      int n = in.nextInt();
      for (int j = 0; j < n; j++) {
        int next = in.nextInt();
        adj[i][next] = true;
        adj[next][i] = true;
      }
    }

    out.println(simulate());

    out.flush();
  }

  static int simulate() {
    int result = -1;

    for (int i = 1; i < (1 << N) - 1; i++) {
      getDistrict(i);
      int diff = getDiff();

      if (diff == -1) {
        continue;
      }

      if (result == -1 || diff < result) {
        result = diff;
      }
    }

    return result;
  }

  static void getDistrict(int n) {
    Arrays.fill(district, -1);

    for (int i = 1; i <= N; i++) {
      district[i] = n & 1;
      n >>= 1;
    }
  }

  static int getDiff() {
    int c = 0;

    Arrays.fill(visited, false);
    for (int i = 1; i <= N; i++) {
      if (!visited[i] && c < 2) {
        bfs(i);
        c += 1;
      }
    }

    for (int i = 1; i <= N; i++) {
      if (!visited[i]) {
        return -1;
      }
    }

    int rSum = 0;
    int bSum = 0;
    for (int i = 1; i <= N; i++) {
      if (district[i] == 0) {
        rSum += populations[i];
      } else {
        bSum += populations[i];
      }
    }

    return Math.abs(rSum - bSum);
  }

  static void bfs(int start) {
    Queue<Integer> q = new LinkedList<>();

    q.add(start);
    visited[start] = true;

    while (!q.isEmpty()) {
      int cur = q.remove();

      for (int next = 1; next <= N; next++) {
        if (visited[next]) {
          continue;
        }

        if (!adj[cur][next] || district[cur] != district[next]) {
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