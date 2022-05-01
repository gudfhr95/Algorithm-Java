import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {

  static int N;
  static int[] X = new int[1000000];
  static TreeMap<Integer, Integer> treeMap = new TreeMap<>();

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    N = in.nextInt();
    for (int i = 0; i < N; i++) {
      X[i] = in.nextInt();
      if (!treeMap.containsKey(X[i])) {
        treeMap.put(X[i], 1);
      }
    }

    int index = 0;
    for (int key : treeMap.keySet()) {
      treeMap.replace(key, index++);
    }

    for (int i = 0; i < N; i++) {
      out.printf("%d ", treeMap.get(X[i]));
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