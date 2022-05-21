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

  static int n, m;
  static boolean[][] friends = new boolean[501][501];
  static int[] dist = new int[501];

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    n = in.nextInt();
    m = in.nextInt();
    for (int i = 0; i < m; i++) {
      int a = in.nextInt();
      int b = in.nextInt();

      friends[a][b] = friends[b][a] = true;
    }

    Queue<Integer> q = new LinkedList<>();
    Arrays.fill(dist, -1);

    q.add(1);
    dist[1] = 0;

    while (!q.isEmpty()) {
      int cur = q.remove();

      for (int i = 1; i <= 500; i++) {
        if (!friends[cur][i]) {
          continue;
        }

        if (dist[i] != -1) {
          continue;
        }

        q.add(i);
        dist[i] = dist[cur] + 1;
      }
    }

    int result = 0;
    for (int i = 2; i <= 500; i++) {
      if (dist[i] != -1 && dist[i] <= 2) {
        result += 1;
      }
    }

    out.println(result);

    out.flush();
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