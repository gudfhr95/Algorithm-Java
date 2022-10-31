class Solution {

  public boolean isToeplitzMatrix(int[][] matrix) {
    for (int y = 0; y < matrix.length; y++) {
      if (!diagonal(matrix, 0, y)) {
        return false;
      }
    }

    for (int x = 0; x < matrix[0].length; x++) {
      if (!diagonal(matrix, x, 0)) {
        return false;
      }
    }

    return true;
  }

  public boolean diagonal(int[][] matrix, int x, int y) {
    int n = matrix[y][x];
    while (0 <= y && y < matrix.length && 0 <= x && x < matrix[y].length) {
      if (matrix[y][x] != n) {
        return false;
      }

      x += 1;
      y += 1;
    }

    return true;
  }
}