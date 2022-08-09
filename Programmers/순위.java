class Solution {

  boolean[][] d = new boolean[101][101];

  public int solution(int n, int[][] results) {
    for (int[] result : results) {
      d[result[0]][result[1]] = true;
    }

    for (int y = 1; y <= n; y++) {
      for (int x = 1; x <= n; x++) {
        for (int k = 1; k <= n; k++) {
          if (d[x][y] && d[y][k]) {
            d[x][k] = true;
          }
        }
      }
    }

    int answer = 0;
    for (int y = 1; y <= n; y++) {
      int count = 0;

      for (int x = 1; x <= n; x++) {
        if (d[y][x] || d[x][y]) {
          count += 1;
        }
      }

      if (count == n - 1) {
        answer += 1;
      }
    }

    return answer;
  }
}
