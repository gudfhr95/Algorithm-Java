import java.util.*;

class Solution {

  static class Pos {

    int x, y, dir, cnt;

    public Pos(int x, int y, int dir, int cnt) {
      this.x = x;
      this.y = y;
      this.dir = dir;
      this.cnt = cnt;
    }
  }

  final int[] dx = {0, 1, 0, -1};
  final int[] dy = {-1, 0, 1, 0};

  char[][] b;

  public String solution(int m, int n, String[] board) {
    b = new char[m][n];

    for (int y = 0; y < board.length; y++) {
      b[y] = board[y].toCharArray();
    }

    String result = "";
    while (true) {
      char c = erase();

      if (c == ' ') {
        break;
      }

      result += c;
    }

    if (result.equals("")) {
      return "IMPOSSIBLE";
    }

    for (int y = 0; y < m; y++) {
      for (int x = 0; x < n; x++) {
        if (b[y][x] != '.' && b[y][x] != '*') {
          return "IMPOSSIBLE";
        }
      }
    }

    return result;
  }

  public char erase() {
    char erased = ' ';
    int x1 = -1;
    int y1 = -1;
    int x2 = -1;
    int y2 = -1;

    for (int y = 0; y < b.length; y++) {
      for (int x = 0; x < b[0].length; x++) {
        if (b[y][x] == '.' || b[y][x] == '*') {
          continue;
        }

        Pos p = bfs(x, y);
        if (p != null) {
          if (erased == ' ' || b[y][x] < erased) {
            erased = b[y][x];
            x1 = x;
            y1 = y;
            x2 = p.x;
            y2 = p.y;
          }
        }
      }
    }

    if (erased != ' ') {
      b[y1][x1] = b[y2][x2] = '.';
    }

    return erased;
  }

  public Pos bfs(int x, int y) {
    Queue<Pos> q = new LinkedList<>();
    boolean[][] visited = new boolean[b.length][b[0].length];

    q.add(new Pos(x, y, -1, -1));
    visited[y][x] = true;

    while (!q.isEmpty()) {
      Pos cur = q.remove();

      for (int k = 0; k < 4; k++) {
        int xn = cur.x + dx[k];
        int yn = cur.y + dy[k];
        int cn = cur.cnt;
        if (cur.dir != k) {
          cn += 1;
        }

        if (cn > 1) {
          continue;
        }

        if (xn < 0 || yn < 0 || xn > b[0].length - 1 || yn > b.length - 1) {
          continue;
        }

        if (visited[yn][xn] || b[yn][xn] == '*') {
          continue;
        }

        if (b[yn][xn] == b[y][x]) {
          return new Pos(xn, yn, 0, 0);
        }

        if (b[yn][xn] == '.') {
          q.add(new Pos(xn, yn, k, cn));
        }
      }
    }

    return null;
  }
}