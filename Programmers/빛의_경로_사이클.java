import java.util.*;

class Solution {

  final int[] dx = {0, 1, 0, -1};
  final int[] dy = {-1, 0, 1, 0};

  int w, h;
  boolean[][][] visited = new boolean[500][500][4];

  public int[] solution(String[] grid) {
    ArrayList<Integer> result = new ArrayList<>();

    h = grid.length;
    w = grid[0].length();

    for (int y = 0; y < h; y++) {
      for (int x = 0; x < w; x++) {
        for (int d = 0; d < 4; d++) {
          if (!visited[y][x][d]) {
            int length = go(grid, x, y, d);
            result.add(length);
          }
        }
      }
    }

    Collections.sort(result);

    return result.stream().mapToInt(Integer::intValue).toArray();
  }

  public int go(String[] grid, int x, int y, int d) {
    int result = 0;

    while (true) {
      if (visited[y][x][d]) {
        break;
      }

      visited[y][x][d] = true;
      result += 1;

      char c = grid[y].charAt(x);
      if (c == 'R') {
        d = (d + 1) % 4;
      } else if (c == 'L') {
        d = (d + 3) % 4;
      }

      x = (x + dx[d] + w) % grid[0].length();
      y = (y + dy[d] + h) % grid.length;
    }

    return result;
  }
}