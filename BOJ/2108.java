import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

  static int N;
  static ArrayList<Integer> numbers = new ArrayList<>();
  static int[] count = new int[8001];

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    N = in.nextInt();
    for (int i = 0; i < N; i++) {
      numbers.add(in.nextInt());
    }

    Collections.sort(numbers);

    out.println(getMean());
    out.println(getMid());
    out.println(getMost());
    out.println(getRange());

    out.flush();
  }

  static int getMean() {
    int result = 0;
    for (int i = 0; i < N; i++) {
      result += numbers.get(i);
    }

    return Math.round(1f * result / N);
  }

  static int getMid() {
    return numbers.get(N / 2);
  }

  static int getMost() {
    for (int i = 0; i < N; i++) {
      count[numbers.get(i) + 4000] += 1;
    }

    int maxValue = 0;
    for (int i = 0; i <= 8000; i++) {
      maxValue = Math.max(maxValue, count[i]);
    }

    ArrayList<Integer> result = new ArrayList<>();
    for (int i = 0; i <= 8000; i++) {
      if (count[i] == maxValue) {
        result.add(i - 4000);
      }
    }

    Collections.sort(result);

    if (result.size() > 1) {
      return result.get(1);
    } else {
      return result.get(0);
    }
  }

  static int getRange() {
    return numbers.get(N - 1) - numbers.get(0);
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