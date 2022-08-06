import java.util.*;

class Solution {

  Set<Integer>[] d = new HashSet[9];

  public int solution(int N, int number) {
    int temp = N;
    for (int i = 1; i <= 8; i++) {
      d[i] = new HashSet<>();
      d[i].add(temp);
      temp = (temp * 10) + N;

      for (int j = 1; j < i; j++) {
        for (int d1 : d[j]) {
          for (int d2 : d[i - j]) {
            d[i].add(d1 + d2);
            d[i].add(d1 - d2);
            d[i].add(d1 * d2);
            if (d2 != 0) {
              d[i].add(d1 / d2);
            }
          }
        }
      }
    }

    for (int i = 1; i <= 8; i++) {
      if (d[i].contains(number)) {
        return i;
      }
    }

    return -1;
  }
}