import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

  static int[] dx = {0, 1, 0, -1};
  static int[] dy = {1, 0, -1, 0};

  static List<Integer> puzzle = new ArrayList<>();
  static HashMap<List<Integer>, Integer> dist = new HashMap<>();

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    for (int i = 0; i < 9; i++) {
      puzzle.add(in.nextInt());
    }

    bfs();

    List<Integer> result = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 0));
    if (!dist.containsKey(Collections.unmodifiableList(result))) {
      out.println(-1);
    } else {
      out.println(dist.get(Collections.unmodifiableList(result)));
    }

    out.flush();
  }

  static void bfs() {
    Queue<List<Integer>> q = new LinkedList<>();

    q.add(puzzle);
    dist.put(Collections.unmodifiableList(puzzle), 0);

    while (!q.isEmpty()) {
      List<Integer> cur = q.remove();

      for (int y = 0; y < 3; y++) {
        for (int x = 0; x < 3; x++) {
          if (cur.get(getIndex(x, y)) == 0) {
            for (int k = 0; k < 4; k++) {
              int xn = x + dx[k];
              int yn = y + dy[k];

              if (xn < 0 || yn < 0 || xn > 2 || yn > 2) {
                continue;
              }

              List<Integer> next = new ArrayList<>(cur);

              swap(next, x, y, xn, yn);

              if (dist.containsKey(Collections.unmodifiableList(next))) {
                continue;
              }

              q.add(next);
              dist.put(Collections.unmodifiableList(next), dist.get(cur) + 1);
            }
          }
        }
      }
    }
  }

  static int getIndex(int x, int y) {
    return y * 3 + x;
  }

  static void swap(List<Integer> m, int x1, int y1, int x2, int y2) {
    int temp = m.get(getIndex(x1, y1));
    m.set(getIndex(x1, y1), m.get(getIndex(x2, y2)));
    m.set(getIndex(x2, y2), temp);
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