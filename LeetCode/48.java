class Solution {

  public void rotate(int[][] matrix) {
    for (int y = 0; y < matrix.length / 2; y++) {
      int size = matrix.length - y * 2;
      for (int x = y; x < y + size - 1; x++) {
        int temp = matrix[y][x];
        matrix[y][x] = matrix[matrix.length - 1 - x][y];
        matrix[matrix.length - 1 - x][y] = matrix[matrix.length - 1 - y][matrix.length - 1 - x];
        matrix[matrix.length - 1 - y][matrix.length - 1 - x] = matrix[x][matrix.length - 1 - y];
        matrix[x][matrix.length - 1 - y] = temp;
      }
    }
  }
}