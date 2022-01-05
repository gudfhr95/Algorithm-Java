import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {

  static LinkedList<Integer> josephus(int n, int k) {
    LinkedList<Integer> result = new LinkedList<>();
    for (int i = 2; i <= n; i++) {
      result.add(i);
    }

    int index = 0;
    while (result.size() != 2) {
      index = (index + k - 1) % result.size();
      result.remove(index);
    }

    return result;
  }

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    int C = in.nextInt();
    while (C-- > 0) {
      LinkedList<Integer> result = josephus(in.nextInt(), in.nextInt());

      for (int elem : result) {
        out.print(elem + " ");
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