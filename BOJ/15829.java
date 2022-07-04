import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Main {

  static int L;
  static String s;

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    L = in.nextInt();
    s = in.next();

    out.println(getHash());

    out.flush();
  }

  static long getHash() {
    long result = 0;
    for (int i = 0; i < L; i++) {
      int num = s.charAt(i) - 'a' + 1;
      result += num * pow(i);
      result %= 1234567891;
    }

    return result;
  }

  static long pow(int n) {
    long result = 1;
    for (int i = 0; i < n; i++) {
      result *= 31;
      result %= 1234567891;
    }

    return result;
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