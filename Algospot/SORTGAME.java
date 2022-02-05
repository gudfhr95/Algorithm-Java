import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

  static HashMap<List<Integer>, Integer> dist = new HashMap<>();

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    for (int i = 1; i <= 8; i++) {
      bfs(i);
    }

    int C = in.nextInt();

    while (C-- > 0) {
      int N = in.nextInt();

      ArrayList<Integer> start = new ArrayList<>();
      for (int i = 0; i < N; i++) {
        start.add(in.nextInt());
      }

      ArrayList<Integer> converted = convert(start);
      out.println(dist.get(Collections.unmodifiableList(converted)));
    }

    out.flush();
  }

  static void bfs(int size) {
    Queue<ArrayList<Integer>> q = new LinkedList<>();

    ArrayList<Integer> start = new ArrayList<>();
    for (int i = 1; i <= size; i++) {
      start.add(i);
    }

    q.add(start);
    dist.put(Collections.unmodifiableList(start), 0);

    while (!q.isEmpty()) {
      ArrayList<Integer> cur = q.remove();

      for (int i = 0; i <= cur.size(); i++) {
        for (int j = i; j <= cur.size(); j++) {
          ArrayList<Integer> next = new ArrayList<>(cur);
          Collections.reverse(next.subList(i, j));

          List<Integer> key = Collections.unmodifiableList(next);
          if (dist.containsKey(key)) {
            continue;
          }

          q.add(next);
          dist.put(key, dist.get(Collections.unmodifiableList(cur)) + 1);
        }
      }
    }
  }

  static ArrayList<Integer> convert(ArrayList<Integer> start) {
    ArrayList<Integer> result = new ArrayList<>();
    for (int i = 0; i < start.size(); i++) {
      int smaller = 1;
      for (int j = 0; j < start.size(); j++) {
        if (start.get(j) < start.get(i)) {
          smaller += 1;
        }
      }
      result.add(smaller);
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