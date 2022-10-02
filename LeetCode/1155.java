class Solution {

  final int MOD = 1_000_000_007;

  int[][] d;

  public int numRollsToTarget(int n, int k, int target) {
    d = new int[n + 1][target + 1];

    for (int a = 1; a <= Math.min(k, target); a++) {
      d[1][a] = 1;
    }

    for (int i = 2; i <= n; i++) {
      for (int s = 0; s <= target; s++) {
        for (int a = 1; a <= k; a++) {
          if (s - a <= 0) {
            continue;
          }

          d[i][s] += d[i - 1][s - a];
          d[i][s] %= MOD;
        }
      }
    }

    return d[n][target];
  }
}