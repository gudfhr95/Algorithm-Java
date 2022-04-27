import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

  static ArrayList<Long> numbers = new ArrayList<>();

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    int N = in.nextInt();

    for (int i = 0; i <= 9; i++) {
      getDecreasingNumbers(i);
    }
    numbers.add(0L);

    Collections.sort(numbers);

    if (N >= numbers.size()) {
      out.println(-1);
    } else {
      out.println(numbers.get(N));
    }

    out.flush();
  }

  static void getDecreasingNumbers(long n) {
    if (n >= 10000000000L || n <= 0) {
      return;
    }

    numbers.add(n);

    long last = n % 10;
    for (long i = last - 1; i >= 0; i--) {
      long newNumber = n * 10 + i;
      getDecreasingNumbers(newNumber);
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