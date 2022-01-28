import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

  static boolean[][] adj = new boolean[26][26];
  static boolean[] visited = new boolean[26];
  static StringBuilder result = new StringBuilder();

  public static void main(String args[]) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    int C = in.nextInt();

    while (C-- > 0) {
      int N = in.nextInt();

      ArrayList<String> words = new ArrayList<>();
      for (int i = 0; i < N; i++) {
        words.add(in.next());
      }

      makeGraph(words);

      out.println(topologicalSort());
    }

    out.flush();
  }

  static void makeGraph(ArrayList<String> words) {
    for (boolean[] a1 : adj) {
      Arrays.fill(a1, false);
    }

    for (int i = 0; i < words.size() - 1; i++) {
      String w1 = words.get(i);
      String w2 = words.get(i + 1);

      for (int j = 0; j < Math.min(w1.length(), w2.length()); j++) {
        int c1 = w1.charAt(j) - 'a';
        int c2 = w2.charAt(j) - 'a';

        if (c1 != c2) {
          adj[c1][c2] = true;
          break;
        }
      }
    }
  }

  static String topologicalSort() {
    Arrays.fill(visited, false);

    result = new StringBuilder();
    for (int i = 0; i < 26; i++) {
      if (!visited[i]) {
        dfs(i);
      }
    }

    for (int i = 0; i < 26; i++) {
      for (int j = i + 1; j < 26; j++) {
        int c1 = result.charAt(i) - 'a';
        int c2 = result.charAt(j) - 'a';

        if (adj[c1][c2]) {
          return "INVALID HYPOTHESIS";
        }
      }
    }

    return result.reverse().toString();
  }

  static void dfs(int start) {
    visited[start] = true;

    for (int next = 0; next < 26; next++) {
      if (adj[start][next] && !visited[next]) {
        dfs(next);
      }
    }

    result.append((char) (start + 'a'));
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