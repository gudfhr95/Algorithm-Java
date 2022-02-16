import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Pos {

  int x, y;

  public Pos(int x, int y) {
    this.x = x;
    this.y = y;
  }
}

public class Main {

  static int N, M, H;
  static int[][] ladder = new int[31][11];

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    N = in.nextInt();
    M = in.nextInt();
    H = in.nextInt();

    for (int i = 0; i < M; i++) {
      int y = in.nextInt();
      int x = in.nextInt();

      ladder[y][x] = 1;
      ladder[y][x + 1] = 2;
    }

    int result = -1;
    if (simulate()) {
      result = 0;
    }

    ArrayList<Pos> lines = getCandidates();
    for (int i = 0; i < lines.size(); i++) {
      Pos l1 = lines.get(i);

      if (!canPut(l1)) {
        continue;
      }

      ladder[l1.y][l1.x] = 1;
      ladder[l1.y][l1.x + 1] = 2;

      if (simulate()) {
        if (result == -1 || result > 1) {
          result = 1;
        }
      }

      for (int j = i + 1; j < lines.size(); j++) {
        Pos l2 = lines.get(j);

        if (!canPut(l2)) {
          continue;
        }

        ladder[l2.y][l2.x] = 1;
        ladder[l2.y][l2.x + 1] = 2;

        if (simulate()) {
          if (result == -1 || result > 2) {
            result = 2;
          }
        }

        for (int k = j + 1; k < lines.size(); k++) {
          Pos l3 = lines.get(k);

          if (!canPut(l3)) {
            continue;
          }

          ladder[l3.y][l3.x] = 1;
          ladder[l3.y][l3.x + 1] = 2;

          if (simulate()) {
            if (result == -1 || result > 3) {
              result = 3;
            }
          }

          ladder[l3.y][l3.x] = 0;
          ladder[l3.y][l3.x + 1] = 0;
        }

        ladder[l2.y][l2.x] = 0;
        ladder[l2.y][l2.x + 1] = 0;
      }

      ladder[l1.y][l1.x] = 0;
      ladder[l1.y][l1.x + 1] = 0;
    }

    out.println(result);

    out.flush();
  }

  static ArrayList<Pos> getCandidates() {
    ArrayList<Pos> result = new ArrayList<>();
    for (int y = 1; y <= H; y++) {
      for (int x = 1; x < N; x++) {
        if (ladder[y][x] == 1 || ladder[y][x] == 2) {
          continue;
        }

        result.add(new Pos(x, y));
      }
    }

    return result;
  }

  static boolean canPut(Pos p) {
    return ladder[p.y][p.x] == 0 && ladder[p.y][p.x + 1] == 0;
  }

  static boolean simulate() {
    for (int i = 1; i <= N; i++) {
      if (!go(i)) {
        return false;
      }
    }
    return true;
  }

  static boolean go(int i) {
    int x = i, y = 1;
    while (y <= H) {
      if (ladder[y][x] == 1 && x < N) {
        x += 1;
      } else if (ladder[y][x] == 2 && x > 1) {
        x -= 1;
      }
      y += 1;
    }

    return x == i;
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