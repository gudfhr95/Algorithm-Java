import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    int N = in.nextInt();
    int M = in.nextInt();

    ArrayList<Integer> a = new ArrayList<>();
    for (int i = 1; i <= N; i++) {
      a.add(i);
    }

    int result = 0;
    for (int i = 0; i < M; i++) {
      int m = in.nextInt();

      int pos = a.indexOf(m);
      if (pos > a.size() / 2) {
        for (int j = 0; j < a.size() - pos; j++) {
          int temp = a.get(a.size() - 1);
          a.remove(a.size() - 1);
          a.add(0, temp);

          result += 1;
        }
      } else {
        for (int j = 0; j < pos; j++) {
          int temp = a.get(0);
          a.remove(0);
          a.add(temp);

          result += 1;
        }
      }

      a.remove(0);
    }

    out.print(result);

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