class Solution {

  long[] d = new long[5001];

  public int solution(int n) {
    d[0] = 1;
    d[2] = 3;

    for (int i = 4; i <= n; i += 2) {
      d[i] = d[i - 2] * 3;
      for (int j = i - 4; j >= 0; j -= 2) {
        d[i] += d[j] * 2;
      }
      d[i] %= 1_000_000_007;
    }

    return (int) d[n];
  }
}