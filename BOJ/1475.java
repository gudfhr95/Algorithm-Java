import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Main {

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    int numbers[] = new int[9];

    String N = in.next();
    for (char c : N.toCharArray()) {
      if (c == '9') {
        numbers[6] += 1;
      } else {
        numbers[c - '0'] += 1;
      }
    }
    numbers[6] = (int) Math.ceil(numbers[6] / 2.0);

    int result = 0;
    for (int i = 0; i < 9; i++) {
      result = Math.max(result, numbers[i]);
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
}
