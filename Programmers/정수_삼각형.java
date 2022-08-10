import java.util.*;

class Solution {

  final int[] dx = {0, 1};

  int width = 0;
  int[][] d = new int[500][500];

  public int solution(int[][] triangle) {
    width = triangle.length;

    for (int[] d1 : d) {
      Arrays.fill(d1, -1);
    }

    return go(triangle, 0, 0);
  }

  public int go(int[][] triangle, int x, int y) {
    if (y == width - 1) {
      return triangle[y][x];
    }

    if (d[y][x] != -1) {
      return d[y][x];
    }

    int result = 0;
    for (int k = 0; k < 2; k++) {
      int xn = x + dx[k];
      int yn = y + 1;

      if (xn > yn) {
        continue;
      }

      result = Math.max(result, go(triangle, xn, yn));
    }
    result += triangle[y][x];

    return d[y][x] = result;
  }
}