import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.StringTokenizer;

public class Main {

  static int N, C;
  static Map<Integer, Integer> hashMap = new LinkedHashMap<>();

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    N = in.nextInt();
    C = in.nextInt();

    for (int i = 0; i < N; i++) {
      int a = in.nextInt();

      if (!hashMap.containsKey(a)) {
        hashMap.put(a, 1);
      } else {
        hashMap.replace(a, hashMap.get(a) + 1);
      }
    }

    List<Entry<Integer, Integer>> list = new LinkedList<>(hashMap.entrySet());
    Collections.sort(list, (o1, o2) -> o2.getValue().compareTo(o1.getValue()));

    for (Entry<Integer, Integer> entry : list) {
      for (int i = 0; i < entry.getValue(); i++) {
        out.println(entry.getKey());
      }
    }

    out.flush();
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
