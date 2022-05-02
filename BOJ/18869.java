import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {

  static int M, N;
  static int[][] planets = new int[100][10000];
  static int[][] multiverse = new int[100][10000];

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    M = in.nextInt();
    N = in.nextInt();
    for (int y = 0; y < M; y++) {
      for (int x = 0; x < N; x++) {
        planets[y][x] = in.nextInt();
      }
    }

    for (int i = 0; i < M; i++) {
      multiverse[i] = convert(i);
    }

    int result = 0;
    for (int i = 0; i < M - 1; i++) {
      for (int j = i + 1; j < M; j++) {
        if (isSame(i, j)) {
          result += 1;
        }
      }
    }

    out.println(result);

    out.flush();
  }

  static int[] convert(int n) {
    TreeMap<Integer, Integer> map = new TreeMap<>();
    for (int i = 0; i < N; i++) {
      map.put(planets[n][i], 1);
    }

    int index = 0;
    for (int key : map.keySet()) {
      map.put(key, index++);
    }

    int[] result = new int[N];
    for (int i = 0; i < N; i++) {
      result[i] = map.get(planets[n][i]);
    }

    return result;
  }

  static boolean isSame(int m1, int m2) {
    for (int i = 0; i < N; i++) {
      if (multiverse[m1][i] != multiverse[m2][i]) {
        return false;
      }
    }
    return true;
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