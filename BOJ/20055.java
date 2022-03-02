import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Main {

  static int N, K;
  static int[] belt = new int[200];
  static int[] robot = new int[100];

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    N = in.nextInt();
    K = in.nextInt();

    for (int i = 0; i < N * 2; i++) {
      belt[i] = in.nextInt();
    }

    for (int t = 1; ; t++) {
      rotate();
      move();

      if (belt[0] > 0) {
        belt[0] -= 1;
        robot[0] += 1;
      }

      if (check()) {
        out.println(t);
        out.flush();

        return;
      }
    }
  }

  static void rotate() {
    int temp = belt[2 * N - 1];
    for (int i = 2 * N - 1; i > 0; i--) {
      belt[i] = belt[i - 1];
    }
    belt[0] = temp;

    for (int i = N - 1; i > 0; i--) {
      robot[i] = robot[i - 1];
    }
    robot[0] = 0;
    robot[N - 1] = 0;
  }

  static void move() {
    for (int i = N - 2; i >= 0; i--) {
      if (robot[i] == 1 && robot[i + 1] == 0 && belt[i + 1] > 0) {
        robot[i + 1] = 1;
        belt[i + 1] -= 1;

        robot[i] = 0;
      }
    }

    robot[N - 1] = 0;
  }

  static boolean check() {
    int count = 0;
    for (int i = 0; i < 2 * N; i++) {
      if (belt[i] == 0) {
        count += 1;
      }
    }

    return count >= K;
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