import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

class Machine implements Comparable<Machine> {

  int id, index;

  public Machine(int id, int index) {
    this.id = id;
    this.index = index;
  }

  @Override
  public int compareTo(Machine m2) {
    return index - m2.index;
  }
}

public class Main {

  static int N, K;
  static int[] order = new int[100];
  static boolean[] plugged = new boolean[101];

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    N = in.nextInt();
    K = in.nextInt();

    for (int i = 0; i < K; i++) {
      order[i] = in.nextInt();
    }

    int result = 0;
    int count = 0;
    for (int i = 0; i < K; i++) {
      int machine = order[i];

      if (plugged[machine]) {
        continue;
      }

      if (count < N) {
        plugged[machine] = true;
        count += 1;

        continue;
      }

      ArrayList<Machine> when = new ArrayList<>();
      for (int j = 1; j <= K; j++) {
        if (!plugged[j]) {
          continue;
        }

        boolean visited = false;
        for (int k = i + 1; k < K; k++) {
          if (order[k] == j) {
            visited = true;
            when.add(new Machine(j, k));
            break;
          }
        }

        if (!visited) {
          when.add(new Machine(j, K + 1));
        }
      }

      Collections.sort(when);
      Machine m = when.get(when.size() - 1);
      plugged[m.id] = false;
      plugged[machine] = true;

      result += 1;
    }

    out.println(result);

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