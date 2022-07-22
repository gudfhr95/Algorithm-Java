class Solution {

  int[][] matrix;

  public int[] solution(int rows, int columns, int[][] queries) {
    matrix = new int[rows + 1][columns + 1];

    int num = 1;
    for (int y = 1; y <= rows; y++) {
      for (int x = 1; x <= columns; x++) {
        matrix[y][x] = num++;
      }
    }

    int[] result = new int[queries.length];
    int index = 0;
    for (int i = 0; i < queries.length; i++) {
      result[index++] = rotate(queries[i][0], queries[i][1], queries[i][2], queries[i][3]);
    }

    return result;
  }

  public int rotate(int y1, int x1, int y2, int x2) {
    int result = matrix[y1][x1];

    int temp = matrix[y1][x1];
    for (int y = y1 + 1; y <= y2; y++) {
      matrix[y - 1][x1] = matrix[y][x1];
      result = Math.min(result, matrix[y][x1]);
    }

    for (int x = x1 + 1; x <= x2; x++) {
      matrix[y2][x - 1] = matrix[y2][x];
      result = Math.min(result, matrix[y2][x]);
    }

    for (int y = y2 - 1; y >= y1; y--) {
      matrix[y + 1][x2] = matrix[y][x2];
      result = Math.min(result, matrix[y][x2]);
    }

    for (int x = x2 - 1; x >= x1; x--) {
      matrix[y1][x + 1] = matrix[y1][x];
      result = Math.min(result, matrix[y1][x]);
    }

    if (x1 + 1 <= matrix[0].length) {
      matrix[y1][x1 + 1] = temp;
    }

    return result;
  }
}