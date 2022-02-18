import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

  static final int[] dx = {0, 1, 1, 1, 0, -1, -1, -1};
  static final int[] dy = {1, 1, 0, -1, -1, -1, 0, 1};

  static int N;
  static PriorityQueue<Integer>[][] trees = new PriorityQueue[11][11];
  static int[][] soil = new int[11][11];
  static int[][] A = new int[11][11];

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    N = in.nextInt();
    int M = in.nextInt();
    int K = in.nextInt();

    for (int y = 1; y <= N; y++) {
      for (int x = 1; x <= N; x++) {
        A[y][x] = in.nextInt();
      }
    }

    init();

    while (M-- > 0) {
      int x = in.nextInt();
      int y = in.nextInt();
      int z = in.nextInt();

      trees[x][y].add(z);
    }

    while (K-- > 0) {
      simulate();
    }

    int result = 0;
    for (int y = 1; y <= N; y++) {
      for (int x = 1; x <= N; x++) {
        result += trees[y][x].size();
      }
    }

    out.println(result);

    out.flush();
  }

  static void init() {
    for (int y = 1; y <= N; y++) {
      for (int x = 1; x <= N; x++) {
        trees[y][x] = new PriorityQueue<>();
        soil[y][x] = 5;
      }
    }
  }

  static void simulate() {
    springSummer();
    fall();
    winter();
  }

  static void springSummer() {
    for (int y = 1; y <= N; y++) {
      for (int x = 1; x <= N; x++) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        while (!trees[y][x].isEmpty() && trees[y][x].peek() <= soil[y][x]) {
          int tree = trees[y][x].remove();
          pq.add(tree + 1);
          soil[y][x] -= tree;
        }

        int dead = 0;
        while (!trees[y][x].isEmpty()) {
          int tree = trees[y][x].remove();
          dead += tree / 2;
        }

        trees[y][x] = pq;
        soil[y][x] += dead;
      }
    }
  }

  static void fall() {
    for (int y = 1; y <= N; y++) {
      for (int x = 1; x <= N; x++) {
        for (int tree : trees[y][x]) {
          if (tree % 5 == 0) {
            breed(x, y);
          }
        }
      }
    }
  }

  static void breed(int x, int y) {
    for (int k = 0; k < 8; k++) {
      int xn = x + dx[k];
      int yn = y + dy[k];

      if (xn < 1 || yn < 1 || xn > N || yn > N) {
        continue;
      }

      trees[yn][xn].add(1);
    }
  }

  static void winter() {
    for (int y = 1; y <= N; y++) {
      for (int x = 1; x <= N; x++) {
        soil[y][x] += A[y][x];
      }
    }
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