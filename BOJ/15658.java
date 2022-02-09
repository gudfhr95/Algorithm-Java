import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Main {

  static int N;
  static int[] A = new int[100];
  static int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    N = in.nextInt();

    for (int i = 0; i < N; i++) {
      A[i] = in.nextInt();
    }

    calculate(1, A[0], in.nextInt(), in.nextInt(), in.nextInt(), in.nextInt());

    out.println(max);
    out.println(min);

    out.flush();
  }

  static void calculate(int index, int result, int plus, int minus, int mult, int div) {
    if (index == N) {
      max = Math.max(max, result);
      min = Math.min(min, result);
      return;
    }

    if (plus > 0) {
      calculate(index + 1, result + A[index], plus - 1, minus, mult, div);
    }
    if (minus > 0) {
      calculate(index + 1, result - A[index], plus, minus - 1, mult, div);
    }
    if (mult > 0) {
      calculate(index + 1, result * A[index], plus, minus, mult - 1, div);
    }
    if (div > 0) {
      calculate(index + 1, result / A[index], plus, minus, mult, div - 1);
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