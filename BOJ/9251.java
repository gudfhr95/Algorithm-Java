import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Main {

  static String A, B;
  static int[][] d = new int[1001][1001];

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    A = in.next();
    B = in.next();

    for (int i = 0; i < A.length(); i++) {
      for (int j = 0; j < B.length(); j++) {
        if (A.charAt(i) == B.charAt(j)) {
          d[i + 1][j + 1] = d[i][j] + 1;
        } else {
          d[i + 1][j + 1] = Math.max(d[i + 1][j], d[i][j + 1]);
        }
      }
    }

    out.println(d[A.length()][B.length()]);

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