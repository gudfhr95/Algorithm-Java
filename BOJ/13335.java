import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

  static int w, L;

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    int n = in.nextInt();
    w = in.nextInt();
    L = in.nextInt();

    Queue<Integer> trucks = new LinkedList<>();
    for (int i = 0; i < n; i++) {
      trucks.add(in.nextInt());
    }

    out.println(simulate(trucks));

    out.flush();
  }

  static int simulate(Queue<Integer> trucks) {
    int result = 0;
    int[] bridge = new int[w];

    while (!trucks.isEmpty()) {
      if (getTotalWeight(bridge) + trucks.peek() <= L) {
        bridge[w - 1] = trucks.remove();
      }
      move(bridge);
      result += 1;
    }

    return result + w;
  }

  static int getTotalWeight(int[] bridge) {
    int result = 0;
    for (int weight : bridge) {
      result += weight;
    }

    return result;
  }

  static void move(int[] bridge) {
    for (int i = 0; i < w - 1; i++) {
      bridge[i] = bridge[i + 1];
    }
    bridge[w - 1] = 0;
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