import java.util.*;

class Solution {

  int[][] matrix;
  int[][] d = new int[200][200];

  public int solution(int[][] matrix_sizes) {
    matrix = matrix_sizes;

    for (int[] d1 : d) {
      Arrays.fill(d1, -1);
    }

    for (int i = 0; i < matrix_sizes.length; i++) {
      d[i][i] = 0;
    }

    return count(0, matrix_sizes.length - 1);
  }

  public int count(int start, int end) {
    if (d[start][end] != -1) {
      return d[start][end];
    }

    int result = Integer.MAX_VALUE;
    for (int k = start; k < end; k++) {
      result = Math.min(
          result,
          count(start, k) + count(k + 1, end) + (matrix[start][0] * matrix[k + 1][0]
              * matrix[end][1])
      );
    }

    return d[start][end] = result;
  }
}