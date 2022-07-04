import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Main {

  static InputReader in = new InputReader(System.in);
  static PrintWriter out = new PrintWriter(System.out);

  static String A, B;
  static int[][] d = new int[1001][1001];

  public static void main(String[] args) {
    A = in.next();
    B = in.next();

    for (int i = 1; i <= A.length(); i++) {
      for (int j = 1; j <= B.length(); j++) {
        if (A.charAt(i - 1) == B.charAt(j - 1)) {
          d[i][j] = d[i - 1][j - 1] + 1;
        } else {
          d[i][j] = Math.max(d[i - 1][j], d[i][j - 1]);
        }
      }
    }

    out.println(d[A.length()][B.length()]);

    print(A.length(), B.length());

    out.flush();
  }

  static void print(int i, int j) {
    if (d[i][j] == 0) {
      return;
    }

    if (A.charAt(i - 1) == B.charAt(j - 1)) {
      print(i - 1, j - 1);
      out.print(A.charAt(i - 1));
    } else if (d[i - 1][j] > d[i][j - 1]) {
      print(i - 1, j);
    } else {
      print(i, j - 1);
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