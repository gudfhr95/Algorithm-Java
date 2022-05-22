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
  static ArrayList<Integer>[] greater = new ArrayList[100];
  static ArrayList<Integer>[] less = new ArrayList[100];

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    N = in.nextInt();
    M = in.nextInt();

    for (int i = 1; i <= N; i++) {
      greater[i] = new ArrayList<>();
      less[i] = new ArrayList<>();
    }

    for (int i = 0; i < M; i++) {
      int a = in.nextInt();
      int b = in.nextInt();

      greater[a].add(b);
      less[b].add(a);
    }

    int result = 0;
    for (int i = 1; i <= N; i++) {
      if (greaterBfs(i) || lessBfs(i)) {
        result += 1;
      }
    }

    out.println(result);

    out.flush();
  }

  static boolean greaterBfs(int n) {
    Queue<Integer> q = new LinkedList<>();
    boolean[] visited = new boolean[N + 1];

    q.add(n);
    visited[n] = true;

    while (!q.isEmpty()) {
      int cur = q.remove();

      for (int next : greater[cur]) {
        if (visited[next]) {
          continue;
        }

        q.add(next);
        visited[next] = true;
      }
    }

    int result = 0;
    for (int i = 1; i <= N; i++) {
      if (visited[i]) {
        result += 1;
      }
    }

    return result > (N + 1) / 2;
  }

  static boolean lessBfs(int n) {
    Queue<Integer> q = new LinkedList<>();
    boolean[] visited = new boolean[N + 1];

    q.add(n);
    visited[n] = true;

    while (!q.isEmpty()) {
      int cur = q.remove();

      for (int next : less[cur]) {
        if (visited[next]) {
          continue;
        }

        q.add(next);
        visited[next] = true;
      }
    }

    int result = 0;
    for (int i = 1; i <= N; i++) {
      if (visited[i]) {
        result += 1;
      }
    }

    return result > (N + 1) / 2;
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