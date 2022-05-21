import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Problem implements Comparable<Problem> {

  int d, ramen;

  public Problem(int d, int ramen) {
    this.d = d;
    this.ramen = ramen;
  }

  @Override
  public int compareTo(Problem other) {
    return ramen - other.ramen;
  }
}

public class Main {

  static int N;
  static PriorityQueue<Problem> pq = new PriorityQueue<>(Collections.reverseOrder());
  static boolean[] available = new boolean[200001];

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    N = in.nextInt();
    for (int i = 0; i < N; i++) {
      pq.add(new Problem(in.nextInt(), in.nextInt()));
    }

    int result = 0;

    Arrays.fill(available, true);
    while (!pq.isEmpty()) {
      Problem p = pq.remove();

      int index = getAvailableIndex(p.d);

      if (index == -1) {
        continue;
      }

      result += p.ramen;
      available[index] = false;
    }

    out.println(result);

    out.flush();
  }

  static int getAvailableIndex(int d) {
    for (int i = d; i >= 1; i--) {
      if (available[i]) {
        return i;
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