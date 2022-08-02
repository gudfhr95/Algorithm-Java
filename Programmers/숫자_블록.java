import java.util.*;

class Solution {

  public int[] solution(long begin, long end) {
    int[] result = new int[(int) (end - begin) + 1];
    int index = 0;

    for (long i = begin; i <= end; i++) {
      result[index++] = getBlock(i);
    }

    return result;
  }


  public int getBlock(long n) {
    if (n == 1) {
      return 0;
    }

    for (long i = 2; i <= Math.sqrt(n); i++) {
      if (n % i == 0 && n / i <= 10000000) {
        return (int) (n / i);
      }
    }

    return 1;
  }
}