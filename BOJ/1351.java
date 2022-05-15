import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {

  static long N, P, Q;
  static HashMap<Long, Long> hashMap = new HashMap<>();

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    N = in.nextLong();
    P = in.nextLong();
    Q = in.nextLong();

    hashMap.put(0L, 1L);

    out.println(sequence(N));

    out.flush();
  }

  static long sequence(long n) {
    if (hashMap.containsKey(n)) {
      return hashMap.get(n);
    }

    long result = sequence(n / P) + sequence(n / Q);
    hashMap.put(n, result);

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

  public long nextLong() {
    return Long.parseLong(next());
  }
}