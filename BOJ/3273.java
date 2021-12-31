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

    int cnt[] = new int[2000000];

    int n = in.nextInt();

    ArrayList<Integer> numbers = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      int a = in.nextInt();
      numbers.add(a);
      cnt[a] += 1;
    }

    int x = in.nextInt();

    int result = 0;
    for (int a : numbers) {
      if (a >= x || x == a * 2) {
        continue;
      } else {
        result += cnt[x - a];
      }
    }
    result /= 2;

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