import java.util.*;

class Solution {

  PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

  public long solution(int n, int[] works) {
    for (int work : works) {
      pq.add(work);
    }

    for (int i = 0; i < n; i++) {
      int work = pq.poll();
      if (work == 0) {
        break;
      }

      pq.add(work - 1);
    }

    long result = 0;
    while (!pq.isEmpty()) {
      result += Math.pow(pq.poll(), 2);
    }

    return result;
  }
}