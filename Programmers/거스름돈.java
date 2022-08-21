import java.util.*;

class Solution {

  final int MOD = 1_000_000_007;

  int[] d = new int[100001];

  public int solution(int n, int[] money) {
    Arrays.sort(money);

    d[0] = 1;
    for (int m : money) {
      for (int c = m; c <= n; c++) {
        d[c] += d[c - m];
        d[c] %= MOD;
      }
    }

    return d[n];
  }
}