import java.io.PrintWriter;

public class Main {

  static int N = 100;

  public static void main(String[] args) {
    PrintWriter out = new PrintWriter(System.out);

    out.println(N);

    for (int y = 0; y < N; y++) {
      for (int x = 0; x < N; x++) {
        if (y == x || y == N - 1 || x == N - 1) {
          out.printf("0 ");
        } else {
          out.printf("1 ");
        }
      }
      out.println();
    }

    out.flush();
  }
}