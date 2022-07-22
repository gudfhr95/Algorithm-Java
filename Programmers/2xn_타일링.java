class Solution {

  int[] d = new int[60001];

  public int solution(int n) {
    d[1] = 1;
    d[2] = 2;

    for (int i = 3; i <= n; i++) {
      d[i] = d[i - 1] + d[i - 2];
      d[i] %= 1_000_000_007;
    }

    return d[n];
  }
}