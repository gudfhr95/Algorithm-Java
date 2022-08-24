import java.util.*;

class Solution {

  public int[] solution(int n, int s) {
    if ((s / n) == 0) {
      return new int[]{-1};
    }

    int[] result = new int[n];

    int q = s / n;
    Arrays.fill(result, q);

    int r = s % n;
    for (int i = n - 1; i > n - 1 - r; i--) {
      result[i] += 1;
    }

    return result;
  }
}