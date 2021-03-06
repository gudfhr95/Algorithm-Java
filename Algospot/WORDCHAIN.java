import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

  static int N;
  static ArrayList<String>[][] graph = new ArrayList[26][26];
  static int[][] adj = new int[26][26];
  static int[] inDegree = new int[26];
  static int[] outDegree = new int[26];

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    int C = in.nextInt();

    while (C-- > 0) {
      N = in.nextInt();

      ArrayList<String> words = new ArrayList<>();
      for (int i = 0; i < N; i++) {
        words.add(in.next());
      }

      makeGraph(words);

      out.println(solve());
    }

    out.flush();
  }

  static void makeGraph(ArrayList<String> words) {
    for (int i = 0; i < 26; i++) {
      for (int j = 0; j < 26; j++) {
        graph[i][j] = new ArrayList<>();
        adj[i][j] = 0;
      }
    }

    Arrays.fill(inDegree, 0);
    Arrays.fill(outDegree, 0);

    for (String word : words) {
      int first = word.charAt(0) - 'a';
      int last = word.charAt(word.length() - 1) - 'a';

      graph[first][last].add(word);
      adj[first][last] += 1;
      inDegree[last] += 1;
      outDegree[first] += 1;
    }
  }

  static String solve() {
    if (!isEuler()) {
      return "IMPOSSIBLE";
    }

    ArrayList<Integer> euler = getEuler();

    if (euler.size() != N + 1) {
      return "IMPOSSIBLE";
    }

    Collections.reverse(euler);

    String result = "";
    for (int i = 0; i < euler.size() - 1; i++) {
      if (result.length() != 0) {
        result += " ";
      }

      result += graph[euler.get(i)][euler.get(i + 1)].get(0);
      graph[euler.get(i)][euler.get(i + 1)].remove(0);
    }

    return result;
  }

  static boolean isEuler() {
    int plus = 0, minus = 0;
    for (int i = 0; i < 26; i++) {
      int diff = outDegree[i] - inDegree[i];

      if (diff > 1 || diff < -1) {
        return false;
      }
      if (diff == 1) {
        plus += 1;
      }
      if (diff == -1) {
        minus += 1;
      }
    }

    return (plus == 0 && minus == 0) || (plus == 1 && minus == 1);
  }

  static ArrayList<Integer> getEuler() {
    ArrayList<Integer> euler = new ArrayList<>();

    for (int i = 0; i < 26; i++) {
      if (outDegree[i] - inDegree[i] == 1) {
        dfs(i, euler);
        return euler;
      }
    }

    for (int i = 0; i < 26; i++) {
      if (outDegree[i] > 0) {
        dfs(i, euler);
        return euler;
      }
    }

    return euler;
  }

  static void dfs(int start, ArrayList<Integer> euler) {
    for (int next = 0; next < 26; next++) {
      if (adj[start][next] > 0) {
        adj[start][next] -= 1;
        dfs(next, euler);
      }
    }
    euler.add(start);
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