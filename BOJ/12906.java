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

  static HashMap<List<String>, Integer> dist = new HashMap<>();

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    List<String> start = new ArrayList<>(3);
    for (int i = 0; i < 3; i++) {
      int n = in.nextInt();
      if (n != 0) {
        start.add(i, in.next());
      } else {
        start.add(i, "");
      }
    }

    bfs(start);

    int[] cnt = new int[3];
    for (int i = 0; i < 3; i++) {
      for (char c : start.get(i).toCharArray()) {
        cnt[c - 'A'] += 1;
      }
    }

    List<String> result = new ArrayList<>();
    for (int i = 0; i < 3; i++) {
      String s = "";
      for (int j = 0; j < cnt[i]; j++) {
        s += (char) (i + 'A');
      }
      result.add(s);
    }

    out.println(dist.get(Collections.unmodifiableList(result)));

    out.flush();
  }

  static void bfs(List<String> start) {
    Queue<List<String>> q = new LinkedList<>();

    q.add(start);
    dist.put(Collections.unmodifiableList(start), 0);

    while (!q.isEmpty()) {
      List<String> cur = q.peek();
      q.remove();

      for (int i = 0; i < 3; i++) {
        if (cur.get(i).length() == 0) {
          continue;
        }

        for (int j = 0; j < 3; j++) {
          if (i == j) {
            continue;
          }

          List<String> next = new ArrayList<>(cur);
          move(next, i, j);

          if (dist.containsKey(Collections.unmodifiableList(next))) {
            continue;
          }

          q.add(next);
          dist.put(Collections.unmodifiableList(next), dist.get(cur) + 1);
        }
      }
    }
  }

  static void move(List<String> list, int i, int j) {
    String from = list.get(i);
    String to = list.get(j);

    to += from.charAt(from.length() - 1);
    from = from.substring(0, from.length() - 1);

    list.set(i, from);
    list.set(j, to);
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