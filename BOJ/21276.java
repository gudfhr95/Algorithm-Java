import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {

  static int N, M;
  static String[] names = new String[1001];
  static TreeMap<String, Integer> map = new TreeMap<>();
  static ArrayList<Integer>[] adj = new ArrayList[1001];
  static int[] indegree = new int[1001];
  static ArrayList<String>[] result = new ArrayList[1001];

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    N = in.nextInt();
    for (int i = 1; i <= N; i++) {
      names[i] = in.next();
      map.put(names[i], i);
      adj[i] = new ArrayList<>();
      result[i] = new ArrayList<>();
    }

    M = in.nextInt();
    for (int i = 0; i < M; i++) {
      String X = in.next();
      String Y = in.next();

      int XIndex = map.get(X);
      int YIndex = map.get(Y);

      adj[YIndex].add(XIndex);
      indegree[XIndex] += 1;
    }

    Queue<Integer> q = new LinkedList<>();
    for (Entry<String, Integer> entry : map.entrySet()) {
      if (indegree[entry.getValue()] == 0) {
        q.add(entry.getValue());
      }
    }

    out.println(q.size());
    for (int index : q) {
      out.printf("%s ", names[index]);
    }
    out.println();

    topologicalSort(q);

    for (Entry<String, Integer> entry : map.entrySet()) {
      out.printf("%s ", entry.getKey());

      ArrayList<String> children = result[entry.getValue()];
      out.printf("%d ", children.size());

      Collections.sort(children);

      for (String child : children) {
        out.printf("%s ", child);
      }

      out.println();
    }

    out.flush();
  }

  static void topologicalSort(Queue<Integer> q) {
    while (!q.isEmpty()) {
      int cur = q.remove();

      for (int next : adj[cur]) {
        indegree[next] -= 1;

        if (indegree[next] == 0) {
          q.add(next);

          result[cur].add(names[next]);
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