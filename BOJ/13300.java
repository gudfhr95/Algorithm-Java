import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Main {

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    int students[][] = new int[2][7];

    int N = in.nextInt();
    int K = in.nextInt();

    for (int i = 0; i < N; i++) {
      int S = in.nextInt();
      int Y = in.nextInt();

      students[S][Y] += 1;
    }

    int result = 0;
    for (int i = 0; i < 2; i++) {
      for (int j = 1; j <= 6; j++) {
        result += (int) Math.ceil(students[i][j] / (double) K);
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