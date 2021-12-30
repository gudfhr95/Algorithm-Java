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

    int N = in.nextInt();

    int Y = 0;
    int M = 0;
    for (int i = 0; i < N; i++) {
      int time = in.nextInt() + 1;

      Y += Math.ceil(time / 30.0) * 10;
      M += Math.ceil(time / 60.0) * 15;
    }

    if (Y < M) {
      out.println("Y " + Y);
    } else if (Y == M) {
      out.println("Y M " + Y);
    } else {
      out.println("M " + M);
    }
    out.flush();
  }
}

class InputReader {

  BufferedReader reader;
  StringTokenizer tokenizer;

  public InputReader(InputStream stream) {
    reader = new BufferedReader(new InputStreamReader(stream), 32768);
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
