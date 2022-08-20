import java.util.*;

class Solution {

  Queue<Integer> q1 = new LinkedList<>();
  Queue<Integer> q2 = new LinkedList<>();

  long sum1 = 0;
  long sum2 = 0;
  int limit = 0;

  public int solution(int[] queue1, int[] queue2) {
    for (int e : queue1) {
      q1.add(e);
      sum1 += e;
    }

    for (int e : queue2) {
      q2.add(e);
      sum2 += e;
    }

    if ((sum1 + sum2) % 2 != 0) {
      return -1;
    }

    limit += (q1.size() + q2.size() + 1);

    int result = 0;
    while (sum1 != sum2) {
      if (result > limit) {
        return -1;
      }

      if (sum1 > sum2) {
        int e = q1.remove();
        sum1 -= e;
        q2.add(e);
        sum2 += e;
      } else if (sum2 > sum1) {
        int e = q2.remove();
        sum2 -= e;
        q1.add(e);
        sum1 += e;
      }

      result += 1;
    }

    return result;
  }
}