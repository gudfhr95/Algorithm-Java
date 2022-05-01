import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {

  static int N;
  static int[] U = new int[1000];
  static TreeMap<Integer, Integer> treeMap = new TreeMap<>();

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    N = in.nextInt();
    for (int i = 0; i < N; i++) {
      U[i] = in.nextInt();
    }

    Arrays.sort(U, 0, N);

    prepare();

    out.println(solve());

    out.flush();
  }

  static void prepare() {
    for (int i = 0; i < N; i++) {
      for (int j = i; j < N; j++) {
        int sum = U[i] + U[j];

        if (!treeMap.containsKey(sum)) {
          treeMap.put(sum, 1);
        }
      }
    }
  }

  static int solve() {
    for (int i = N - 1; i > 0; i--) {
      for (int j = 0; j < i; j++) {
        int diff = U[i] - U[j];

        if (treeMap.containsKey(diff)) {
          return U[i];
        }
      }
    }

    return -1;
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