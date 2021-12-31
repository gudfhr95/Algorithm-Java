import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    int T = in.nextInt();

    for (int t = 0; t < T; t++) {
      LinkedList<Character> password = new LinkedList<>();

      String L = in.next();
      int cursor = 0;
      for (char c : L.toCharArray()) {
        if (c == '<') {
          cursor = cursor == 0 ? 0 : cursor - 1;
        } else if (c == '>') {
          cursor = cursor == password.size() ? password.size() : cursor + 1;
        } else if (c == '-') {
          if (cursor == 0) {
            continue;
          } else {
            password.remove(--cursor);
          }
        } else {
          password.add(cursor++, c);
        }
      }

      for (char c : password) {
        out.print(c);
      }
      out.println();
    }
    
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