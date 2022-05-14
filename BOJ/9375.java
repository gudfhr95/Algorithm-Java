import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {

  static HashMap<String, Integer> clothes = new HashMap<>();

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    int t = in.nextInt();
    while (t-- > 0) {
      clothes.clear();

      int n = in.nextInt();
      for (int i = 0; i < n; i++) {
        String name = in.next();
        String category = in.next();

        if (!clothes.containsKey(category)) {
          clothes.put(category, 0);
        }

        int count = clothes.get(category);
        clothes.replace(category, count + 1);
      }

      int result = 1;
      for (String category : clothes.keySet()) {
        int count = clothes.get(category);

        result *= (count + 1);
      }

      out.println(result - 1);
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