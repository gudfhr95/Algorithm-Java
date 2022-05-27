import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

  static int N, target;
  static int[] parents = new int[50];
  static ArrayList<Integer>[] adj = new ArrayList[50];
  static boolean[] erased = new boolean[50];

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    N = in.nextInt();
    for (int i = 0; i < N; i++) {
      adj[i] = new ArrayList<>();
    }

    for (int i = 0; i < N; i++) {
      parents[i] = in.nextInt();

      if (parents[i] != -1) {
        adj[i].add(parents[i]);
        adj[parents[i]].add(i);
      }
    }

    target = in.nextInt();

    erase(target);

    boolean[] hasChild = new boolean[N];
    for (int i = 0; i < N; i++) {
      if (parents[i] == -1 || erased[i]) {
        continue;
      }

      hasChild[parents[i]] = true;
    }

    int result = 0;
    for (int i = 0; i < N; i++) {
      if (erased[i]) {
        continue;
      }

      if (!hasChild[i]) {
        result += 1;
      }
    }

    out.println(result);

    out.flush();
  }

  static void erase(int n) {
    erased[n] = true;

    for (int next : adj[n]) {
      if (next == parents[n]) {
        continue;
      }

      if (erased[next]) {
        continue;
      }

      erase(next);
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