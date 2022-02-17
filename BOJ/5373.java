import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Main {

  static final int UP = 0, DOWN = 1, FRONT = 2, BACK = 3, LEFT = 4, RIGHT = 5;
  static final char[] colors = {'w', 'y', 'r', 'o', 'g', 'b'};
  static char[][][] cube = new char[6][3][3];

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    int T = in.nextInt();
    while (T-- > 0) {
      int n = in.nextInt();

      init();

      while (n-- > 0) {
        String c = in.next();

        char side = c.charAt(0);
        int dir = c.charAt(1) == '+' ? 1 : 3;

        for (int i = 0; i < dir; i++) {
          if (side == 'U') {
            rotateUp();
          } else if (side == 'D') {
            rotateDown();
          } else if (side == 'F') {
            rotateFront();
          } else if (side == 'B') {
            rotateBack();
          } else if (side == 'L') {
            rotateLeft();
          } else if (side == 'R') {
            rotateRight();
          }
        }
      }

      for (int y = 0; y < 3; y++) {
        for (int x = 0; x < 3; x++) {
          out.print(cube[UP][y][x]);
        }
        out.println();
      }
    }

    out.flush();
  }

  static void init() {
    for (int z = 0; z < 6; z++) {
      for (int y = 0; y < 3; y++) {
        for (int x = 0; x < 3; x++) {
          cube[z][y][x] = colors[z];
        }
      }
    }
  }

  static void rotateUp() {
    rotate(UP);

    for (int i = 0; i < 3; i++) {
      char temp = cube[FRONT][0][i];
      cube[FRONT][0][i] = cube[RIGHT][0][i];
      cube[RIGHT][0][i] = cube[BACK][0][i];
      cube[BACK][0][i] = cube[LEFT][0][i];
      cube[LEFT][0][i] = temp;
    }
  }

  static void rotateDown() {
    rotate(DOWN);

    for (int i = 0; i < 3; i++) {
      char temp = cube[FRONT][2][i];
      cube[FRONT][2][i] = cube[LEFT][2][i];
      cube[LEFT][2][i] = cube[BACK][2][i];
      cube[BACK][2][i] = cube[RIGHT][2][i];
      cube[RIGHT][2][i] = temp;
    }
  }

  static void rotateFront() {
    rotate(FRONT);

    for (int i = 0; i < 3; i++) {
      char temp = cube[UP][2][i];
      cube[UP][2][i] = cube[LEFT][2 - i][2];
      cube[LEFT][2 - i][2] = cube[DOWN][0][2 - i];
      cube[DOWN][0][2 - i] = cube[RIGHT][i][0];
      cube[RIGHT][i][0] = temp;
    }
  }

  static void rotateBack() {
    rotate(BACK);

    for (int i = 0; i < 3; i++) {
      char temp = cube[UP][0][i];
      cube[UP][0][i] = cube[RIGHT][i][2];
      cube[RIGHT][i][2] = cube[DOWN][2][2 - i];
      cube[DOWN][2][2 - i] = cube[LEFT][2 - i][0];
      cube[LEFT][2 - i][0] = temp;
    }
  }

  static void rotateLeft() {
    rotate(LEFT);

    for (int i = 0; i < 3; i++) {
      char temp = cube[UP][i][0];
      cube[UP][i][0] = cube[BACK][2 - i][2];
      cube[BACK][2 - i][2] = cube[DOWN][i][0];
      cube[DOWN][i][0] = cube[FRONT][i][0];
      cube[FRONT][i][0] = temp;
    }
  }

  static void rotateRight() {
    rotate(RIGHT);

    for (int i = 0; i < 3; i++) {
      char temp = cube[UP][i][2];
      cube[UP][i][2] = cube[FRONT][i][2];
      cube[FRONT][i][2] = cube[DOWN][i][2];
      cube[DOWN][i][2] = cube[BACK][2 - i][0];
      cube[BACK][2 - i][0] = temp;
    }
  }

  static void rotate(int side) {
    char[][] result = new char[3][3];
    for (int y = 0; y < 3; y++) {
      for (int x = 0; x < 3; x++) {
        result[y][x] = cube[side][2 - x][y];
      }
    }
    cube[side] = result;
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