import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

class Pos {

  int x, y, d;

  public Pos(int x, int y, int d) {
    this.x = x;
    this.y = y;
    this.d = d;
  }
}

public class Main {

  static int[] dx = {0, -1, -1, -1, 0, 1, 1, 1};
  static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};

  static int[][] space = new int[4][4];
  static Pos[] fishes = new Pos[17];
  static int result = 0;

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    for (int y = 0; y < 4; y++) {
      for (int x = 0; x < 4; x++) {
        int a = in.nextInt();
        int b = in.nextInt();

        space[y][x] = a;
        fishes[a] = new Pos(x, y, b - 1);
      }
    }

    int num = space[0][0];
    int d = fishes[num].d;
    space[0][0] = 0;
    fishes[num] = null;

    simulate(0, 0, d, num);

    out.println(result);

    out.flush();
  }

  static void simulate(int x, int y, int d, int sum) {
    int[][] originalSpace = space;
    Pos[] originalFishes = fishes;

    move(x, y);

    for (int k = 1; k < 4; k++) {
      int xn = x + dx[d] * k;
      int yn = y + dy[d] * k;

      if (xn < 0 || yn < 0 || xn > 3 || yn > 3) {
        continue;
      }

      if (space[yn][xn] == 0) {
        continue;
      }

      int tempNum = space[yn][xn];
      Pos tempFish = fishes[tempNum];

      space[yn][xn] = 0;
      fishes[tempNum] = null;

      simulate(xn, yn, tempFish.d, sum + tempNum);

      space[yn][xn] = tempNum;
      fishes[tempNum] = tempFish;
    }

    result = Math.max(result, sum);

    space = originalSpace;
    fishes = originalFishes;
  }

  static void move(int x, int y) {
    int[][] resultSpace = new int[4][4];
    Pos[] resultFishes = new Pos[17];

    for (int r = 0; r < 4; r++) {
      for (int c = 0; c < 4; c++) {
        resultSpace[r][c] = space[r][c];
      }
    }

    for (int i = 1; i <= 16; i++) {
      if (fishes[i] == null) {
        resultFishes[i] = null;
      } else {
        resultFishes[i] = new Pos(fishes[i].x, fishes[i].y, fishes[i].d);
      }
    }

    for (int i = 1; i <= 16; i++) {
      if (resultFishes[i] == null) {
        continue;
      }

      moveFish(i, x, y, resultSpace, resultFishes);
    }

    space = resultSpace;
    fishes = resultFishes;
  }

  static void moveFish(int num, int x, int y, int[][] space, Pos[] fishes) {
    Pos fish = fishes[num];
    for (int k = 0; k < 8; k++) {
      int dn = (fish.d + k) % 8;
      int xn = fish.x + dx[dn];
      int yn = fish.y + dy[dn];

      if (xn < 0 || yn < 0 || xn > 3 || yn > 3) {
        continue;
      }

      if (xn == x && yn == y) {
        continue;
      }

      if (space[yn][xn] == 0) {
        space[yn][xn] = space[fish.y][fish.x];
        space[fish.y][fish.x] = 0;

        fish.x = xn;
        fish.y = yn;
        fish.d = dn;
      } else {
        swap(fish.x, fish.y, xn, yn, dn, space, fishes);
      }

      return;
    }
  }

  static void swap(int x1, int y1, int x2, int y2, int d, int[][] space, Pos[] fishes) {
    int num1 = space[y1][x1];
    int num2 = space[y2][x2];

    Pos fish1 = fishes[num1];
    Pos fish2 = fishes[num2];

    int tempNum = num1;
    space[y1][x1] = space[y2][x2];
    space[y2][x2] = tempNum;

    int tempX = fish1.x;
    fish1.x = fish2.x;
    fish2.x = tempX;

    int tempY = fish1.y;
    fish1.y = fish2.y;
    fish2.y = tempY;

    fish1.d = d;
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