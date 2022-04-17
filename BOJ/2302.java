import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Main {

  static int N, M;
  static boolean[] VIP = new boolean[41];
  static int[] cache = new int[41];

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    N = in.nextInt();
    M = in.nextInt();

    for (int i = 0; i < M; i++) {
      VIP[in.nextInt()] = true;
    }

    cache[0] = cache[1] = 1;
    cache[2] = 2;
    for (int i = 3; i <= 40; i++) {
      cache[i] = cache[i - 1] + cache[i - 2];
    }

    int result = 1;

    int cur = 0;
    for (int i = 1; i <= N; i++) {
      if (VIP[i]) {
        result *= cache[i - cur - 1];
        cur = i;
      }
    }
    result *= cache[N - cur];

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