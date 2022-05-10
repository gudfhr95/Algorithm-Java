import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Main {

  static int N, d, k, c;
  static int[] sushi = new int[30000];

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    N = in.nextInt();
    d = in.nextInt();
    k = in.nextInt();
    c = in.nextInt();
    for (int i = 0; i < N; i++) {
      sushi[i] = in.nextInt();
    }

    int result = 0;

    int sum = 0;
    int[] eaten = new int[3001];
    for (int i = 0; i < k; i++) {
      if (eaten[sushi[i]] == 0) {
        sum += 1;
      }
      eaten[sushi[i]] += 1;
    }

    if (eaten[c] == 0) {
      result = Math.max(result, sum + 1);
    } else {
      result = Math.max(result, sum);
    }

    for (int i = k; i < N + k; i++) {
      eaten[sushi[(i - k) % N]] -= 1;
      if (eaten[sushi[(i - k) % N]] == 0) {
        sum -= 1;
      }

      if (eaten[sushi[i % N]] == 0) {
        sum += 1;
      }
      eaten[sushi[i % N]] += 1;

      if (eaten[c] == 0) {
        result = Math.max(result, sum + 1);
      } else {
        result = Math.max(result, sum);
      }
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