import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Main {

  static int C;

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    C = in.nextInt();

    while (C-- > 0) {
      out.println(quadTree(in.next()));
    }

    out.flush();
  }

  static String quadTree(String s) {
    if (s.length() == 1) {
      return s;
    }

    String[] results = new String[4];
    if (s.charAt(0) == 'x') {
      s = s.substring(1);

      int[] indexes = split(s);
      int cur = 0;
      for (int i = 0; i < indexes.length; i++) {
        results[i] = quadTree(s.substring(cur, indexes[i] + 1));
        cur = indexes[i] + 1;
      }
    }

    return "x" + results[2] + results[3] + results[0] + results[1];
  }

  static int[] split(String s) {
    int[] result = new int[4];
    int index = 0;
    for (int i = 0; i < s.length(); i++) {
      if (s.charAt(i) == 'x') {
        int count = 4;

        while (count-- > 0) {
          i += 1;

          if (s.charAt(i) == 'x') {
            count += 4;
          }
        }
      }
      result[index++] = i;
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