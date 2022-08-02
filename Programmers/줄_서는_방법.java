import java.util.*;

class Solution {

  public int[] solution(int n, long k) {
    ArrayList<Integer> people = new ArrayList<>();
    for (int i = 1; i <= n; i++) {
      people.add(i);
    }

    long f = 1;
    for (int i = 1; i <= n; i++) {
      f *= i;
    }

    int[] result = new int[n];
    int index = 0;
    k -= 1;
    while (n > 0) {
      f /= n;

      int i = (int) (k / f);
      result[index++] = people.get(i);
      people.remove(i);

      k %= f;
      n -= 1;
    }

    return result;
  }
}
