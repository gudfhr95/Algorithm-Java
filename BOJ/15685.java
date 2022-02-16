import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

  static int[][] grid = new int[101][101];

  public static void main(String[] args) {
    InputRedaer in = new InputRedaer(System.in);
    PrintWriter out = new PrintWriter(System.out);

    int N = in.nextInt();
    while (N-- > 0) {
      int x = in.nextInt();
      int y = in.nextInt();
      int d = in.nextInt();
      int g = in.nextInt();

      ArrayList<Integer> directions = new ArrayList<>();
      directions.add(d);

      while (g-- > 0) {
        int size = directions.size();
        for (int i = size - 1; i >= 0; i--) {
          directions.add((directions.get(i) + 1) % 4);
        }
      }

      grid[y][x] = 1;
      for (Integer direction : directions) {
        if (direction == 0) {
          x += 1;
        } else if (direction == 1) {
          y -= 1;
        } else if (direction == 2) {
          x -= 1;
        } else if (direction == 3) {
          y += 1;
        }

        grid[y][x] = 1;
      }
    }

    int result = 0;
    for (int y = 0; y < 100; y++) {
      for (int x = 0; x < 100; x++) {
        if (grid[y][x] == 1 && grid[y + 1][x] == 1 && grid[y][x + 1] == 1
            && grid[y + 1][x + 1] == 1) {
          result += 1;
        }
      }
    }

    out.println(result);

    out.flush();
  }
}

class InputRedaer {

  BufferedReader reader;
  StringTokenizer tokenizer;

  public InputRedaer(InputStream stream) {
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