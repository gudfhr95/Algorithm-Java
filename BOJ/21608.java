import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

  static final int[] dx = {0, 1, 0, -1};
  static final int[] dy = {-1, 0, 1, 0};

  static int N;
  static ArrayList<Integer>[] favorites = new ArrayList[1000];
  static int[][] seats = new int[100][100];

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    N = in.nextInt();

    for (int i = 0; i < N * N; i++) {
      int num = in.nextInt();

      favorites[num] = new ArrayList<>();
      for (int j = 0; j < 4; j++) {
        favorites[num].add(in.nextInt());
      }

      seatStudent(num);
    }

    int result = 0;
    for (int y = 0; y < N; y++) {
      for (int x = 0; x < N; x++) {
        int favoriteSum = getFavoriteSum(seats[y][x], x, y);

        if (favoriteSum == 0) {
          continue;
        }

        result += Math.pow(10, favoriteSum - 1);
      }
    }

    out.println(result);

    out.flush();
  }

  static void seatStudent(int n) {
    int r = 0;
    int c = 0;
    int f = -1;
    int e = -1;

    for (int y = 0; y < N; y++) {
      for (int x = 0; x < N; x++) {
        if (seats[y][x] != 0) {
          continue;
        }

        int favoriteSum = getFavoriteSum(n, x, y);
        int emptySum = getEmptySum(n, x, y);
        if (favoriteSum > f || (favoriteSum == f && emptySum > e)) {
          f = favoriteSum;
          e = emptySum;

          r = y;
          c = x;
        }
      }
    }

    seats[r][c] = n;
  }

  static int getFavoriteSum(int n, int x, int y) {
    int result = 0;

    for (int k = 0; k < 4; k++) {
      int xn = x + dx[k];
      int yn = y + dy[k];

      if (xn < 0 || yn < 0 || xn > N - 1 || yn > N - 1) {
        continue;
      }

      if (favorites[n].contains(seats[yn][xn])) {
        result += 1;
      }
    }

    return result;
  }

  static int getEmptySum(int n, int x, int y) {
    int result = 0;

    for (int k = 0; k < 4; k++) {
      int xn = x + dx[k];
      int yn = y + dy[k];

      if (xn < 0 || yn < 0 || xn > N - 1 || yn > N - 1) {
        continue;
      }

      if (seats[yn][xn] == 0) {
        result += 1;
      }
    }

    return result;
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