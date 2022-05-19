import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {

  static int N;
  static int[] heights = new int[250001];
  static TreeSet<Integer> tree = new TreeSet<>();

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    N = in.nextInt();

    long result = 0;
    for (int i = 0; i < N; i++) {
      result += getHeight(in.nextInt());
    }

    out.println(result);

    out.flush();
  }

  static long getHeight(int n) {
    Integer upperBound = tree.higher(n);
    Integer lowerBound = tree.lower(n);

    tree.add(n);

    if (upperBound == null && lowerBound == null) {
      return heights[n] = 1;
    }

    if (upperBound != null && lowerBound == null) {
      return heights[n] = (heights[upperBound] + 1);
    }

    if (upperBound == null && lowerBound != null) {
      return heights[n] = (heights[lowerBound] + 1);
    }

    int height = Math.max(heights[upperBound], heights[lowerBound]) + 1;
    return heights[n] = height;
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