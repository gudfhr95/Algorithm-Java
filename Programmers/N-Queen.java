class Solution {

  final int dx[] = {1, 1, -1, -1};
  final int dy[] = {-1, 1, 1, -1};

  boolean[][] board;
  int result = 0;

  public int solution(int n) {
    board = new boolean[n][n];

    queen(0);

    return result;
  }

  public void queen(int n) {
    if (n == board.length) {
      result += 1;
      return;
    }

    for (int x = 0; x < board.length; x++) {
      if (check(x, n)) {
        board[n][x] = true;
        queen(n + 1);
        board[n][x] = false;
      }
    }
  }

  public boolean check(int x, int y) {
    for (int r = 0; r < board.length; r++) {
      if (board[r][x]) {
        return false;
      }
    }

    for (int d = 0; d < board.length; d++) {
      for (int k = 0; k < 4; k++) {
        int xn = x + dx[k] * d;
        int yn = y + dy[k] * d;

        if (xn < 0 || yn < 0 || xn >= board.length || yn >= board.length) {
          continue;
        }

        if (board[yn][xn]) {
          return false;
        }
      }
    }

    return true;
  }
}