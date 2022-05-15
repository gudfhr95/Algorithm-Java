import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {

  static int T, k;
  static TreeMap<Integer, Integer> map = new TreeMap<>(Collections.reverseOrder());

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    T = in.nextInt();
    while (T-- > 0) {
      k = in.nextInt();

      map.clear();
      for (int i = 0; i < k; i++) {
        String op = in.next();
        int n = in.nextInt();

        if (op.equals("I")) {
          insert(n);
        } else {
          delete(n);
        }
      }

      if (map.isEmpty()) {
        out.println("EMPTY");
      } else {
        out.printf("%d %d\n", map.firstKey(), map.lastKey());
      }
    }

    out.flush();
  }

  static void insert(int n) {
    if (!map.containsKey(n)) {
      map.put(n, 0);
    }

    int count = map.get(n);
    map.replace(n, count + 1);
  }

  static void delete(int n) {
    if (map.isEmpty()) {
      return;
    }

    int key;
    if (n == 1) {
      key = map.firstKey();
    } else {
      key = map.lastKey();
    }

    int count = map.get(key);
    if (count == 1) {
      map.remove(key);
    } else {
      map.replace(key, count - 1);
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
