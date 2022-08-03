import java.util.*;

class Solution {

  List<List<Integer>> result = new ArrayList<>();

  public List<List<Integer>> solution(int n) {
    hanoi(n, 1, 3, 2);

    return result;
  }

  public void hanoi(int n, int from, int to, int mid) {
    if (n == 1) {
      result.add(Arrays.asList(from, to));
      return;
    }

    hanoi(n - 1, from, mid, to);
    result.add(Arrays.asList(from, to));
    hanoi(n - 1, mid, to, from);
  }
}