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
  static int[] dist = new int[1 << (12 * 2)];

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    int C = in.nextInt();

    while (C-- > 0) {
      N = in.nextInt();

      int start = 0;
      for (int i = 0; i < 4; i++) {
        int n = in.nextInt();

        for (int j = 0; j < n; j++) {
          start = set(start, in.nextInt() - 1, i);
        }
      }

      int end = 0;
      for (int i = 0; i < N; i++) {
        end = set(end, i, 3);
      }

      out.println(bfs(start, end));
    }

    out.flush();
  }

  static int bfs(int start, int end) {
    if (start == end) {
      return 0;
    }

    Queue<Integer> q = new LinkedList<>();
    Arrays.fill(dist, 0);

    q.add(start);
    dist[start] = 1;

    q.add(end);
    dist[end] = -1;

    while (!q.isEmpty()) {
      int cur = q.remove();

      int[] top = {-1, -1, -1, -1};
      for (int i = N - 1; i >= 0; i--) {
        top[get(cur, i)] = i;
      }

      for (int i = 0; i < 4; i++) {
        if (top[i] == -1) {
          continue;
        }

        for (int j = 0; j < 4; j++) {
          if (i == j) {
            continue;
          }

          if (top[j] != -1 && top[j] < top[i]) {
            continue;
          }

          int next = set(cur, top[i], j);
          if (dist[next] == 0) {
            q.add(next);
            dist[next] = increase(dist[cur]);
          } else if (sign(dist[next]) != sign(dist[cur])) {
            return Math.abs(dist[next]) + Math.abs(dist[cur]) - 1;
          }
        }
      }
    }

    return -1;
  }

  static int get(int state, int index) {
    return (state >> (index * 2)) & 3;
  }

  static int set(int state, int index, int value) {
    return (state & ~(3 << (index * 2))) | (value << (index * 2));
  }

  static int increase(int x) {
    return x > 0 ? x + 1 : x - 1;
  }

  static int sign(int x) {
    if (x == 0) {
      return 0;
    }
    return x > 0 ? 1 : -1;
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