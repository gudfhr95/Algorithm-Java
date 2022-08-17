import java.util.*;

class Solution {

  class Pos {

    int x, y;

    Pos(int x, int y) {
      this.x = x;
      this.y = y;
    }
  }

  final int[] dx = {0, 1, 0, -1};
  final int[] dy = {-1, 0, 1, 0};

  int[][] map = new int[101][101];
  int[][] d = new int[101][101];

  public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
    makeMap(rectangle);

    for (int[] d1 : d) {
      Arrays.fill(d1, -1);
    }

    return bfs(characterX, characterY, itemX, itemY);
  }

  public void makeMap(int[][] rectangle) {
    for (int[] r : rectangle) {
      int x1 = r[0] * 2;
      int y1 = r[1] * 2;
      int x2 = r[2] * 2;
      int y2 = r[3] * 2;

      for (int x = x1; x <= x2; x++) {
        if (map[y1][x] != 2) {
          map[y1][x] = 1;
        }
        if (map[y2][x] != 2) {
          map[y2][x] = 1;
        }
      }

      for (int y = y1; y <= y2; y++) {
        if (map[y][x1] != 2) {
          map[y][x1] = 1;
        }
        if (map[y][x2] != 2) {
          map[y][x2] = 1;
        }
      }

      for (int y = y1 + 1; y < y2; y++) {
        for (int x = x1 + 1; x < x2; x++) {
          map[y][x] = 2;
        }
      }
    }
  }

  public int bfs(int cx, int cy, int ix, int iy) {
    Queue<Pos> q = new LinkedList<>();

    q.add(new Pos(cx * 2, cy * 2));
    d[cy * 2][cx * 2] = 0;

    while (!q.isEmpty()) {
      Pos cur = q.remove();

      for (int k = 0; k < 4; k++) {
        int xn = cur.x + dx[k];
        int yn = cur.y + dy[k];

        if (xn < 1 || yn < 1 || xn > 100 || yn > 100) {
          continue;
        }

        if (map[yn][xn] != 1) {
          continue;
        }

        if (d[yn][xn] != -1) {
          continue;
        }

        q.add(new Pos(xn, yn));
        d[yn][xn] = d[cur.y][cur.x] + 1;
      }
    }

    return d[iy * 2][ix * 2] / 2;
  }
}