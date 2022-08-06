class Solution {

  public long solution(int n, int[] times) {
    long l = 0;
    long r = 1_000_000_000L * 1_000_000_000L;

    long result = 0;
    while (l <= r) {
      long m = (l + r) / 2;

      if (canFinish(m, n, times)) {
        result = m;
        r = m - 1;
      } else {
        l = m + 1;
      }
    }

    return result;
  }

  public boolean canFinish(long m, int n, int[] times) {
    long sum = 0;
    for (int time : times) {
      sum += (m / time);
    }

    return sum >= n;
  }
}