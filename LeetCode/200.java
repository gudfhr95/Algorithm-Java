class Solution {

  final int[] dx = {0, 1, 0, -1};
  final int[] dy = {-1, 0, 1, 0};

  class Pos {

    int x, y;

    Pos(int x, int y) {
      this.x = x;
      this.y = y;
    }
  }

  int m, n;
  boolean[][] visited;

  public int numIslands(char[][] grid) {
    m = grid.length;
    n = grid[0].length;

    visited = new boolean[m][n];

    int result = 0;
    for (int y = 0; y < grid.length; y++) {
      for (int x = 0; x < grid[y].length; x++) {
        if (visited[y][x] || grid[y][x] == '0') {
          continue;
        }

        bfs(grid, x, y);

        result += 1;
      }
    }

    return result;
  }

  public void bfs(char[][] grid, int x, int y) {
    Queue<Pos> q = new LinkedList<>();

    q.add(new Pos(x, y));
    visited[y][x] = true;

    while (!q.isEmpty()) {
      Pos cur = q.poll();

      for (int k = 0; k < 4; k++) {
        int xn = cur.x + dx[k];
        int yn = cur.y + dy[k];

        if (xn < 0 || yn < 0 || xn > n - 1 || yn > m - 1) {
          continue;
        }

        if (visited[yn][xn]) {
          continue;
        }

        if (grid[yn][xn] == '0') {
          continue;
        }

        q.add(new Pos(xn, yn));
        visited[yn][xn] = true;
      }
    }
  }
}
