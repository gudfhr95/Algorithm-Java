class Solution {

  public int[] findBall(int[][] grid) {
    int[] result = new int[grid[0].length];
    for (int x = 0; x < grid[0].length; x++) {
      result[x] = go(grid, x);
    }

    return result;
  }

  public int go(int[][] grid, int x) {
    int y = 0;
    while (y < grid.length) {
      int xn = x + grid[y][x];

      if (xn < 0 || xn >= grid[0].length) {
        return -1;
      }

      if (grid[y][x] + grid[y][xn] == 0) {
        return -1;
      }

      x = xn;
      y += 1;
    }

    return x;
  }
}