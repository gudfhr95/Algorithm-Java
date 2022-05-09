import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Main {

  static int N, K;
  static int[] S = new int[1000000];

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    N = in.nextInt();
    K = in.nextInt();
    for (int i = 0; i < N; i++) {
      S[i] = in.nextInt();
    }

    int result = 0;

    int s = 0, e = 0;
    int length = 0;
    int erased = 0;
    while (e < N) {
      if (S[e] % 2 == 0) {
        length += 1;
        e += 1;
      } else {
        if (erased < K) {
          erased += 1;
          e += 1;
        } else {
          result = Math.max(result, length);
          if (S[s] % 2 == 0) {
            length -= 1;
            s += 1;
          } else {
            erased -= 1;
            s += 1;
          }
        }
      }
    }

    result = Math.max(result, length);
    
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