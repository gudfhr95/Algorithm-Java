class Solution {

  final int MOD = 1_000_000_007;

  int[][] map;
  int[][] d;

  public int solution(int m, int n, int[][] puddles) {
    map = new int[n + 1][m + 1];
    d = new int[n + 1][m + 1];

    for (int y = 1; y <= n; y++) {
      for (int x = 1; x <= m; x++) {
        map[y][x] = 1;
        d[y][x] = -1;
      }
    }

    for (int[] puddle : puddles) {
      map[puddle[1]][puddle[0]] = 0;
    }

    d[1][1] = 0;
    d[1][2] = d[2][1] = 1;
    return go(m, n) % MOD;
  }

  public int go(int x, int y) {
    if (x < 1 || y < 1) {
      return 0;
    }

    if (map[y][x] == 0) {
      return 0;
    }

    if (d[y][x] != -1) {
      return d[y][x];
    }

    int result = go(x - 1, y) + go(x, y - 1);
    result %= MOD;

    return d[y][x] = result;
  }
}