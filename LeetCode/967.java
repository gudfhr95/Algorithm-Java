class Solution {

  List<Integer> result = new ArrayList<>();

  public int[] numsSameConsecDiff(int n, int k) {
    for (int i = 1; i <= 9; i++) {
      getNumsSameConsecDiff(i, n - 1, k);
    }

    return result.stream()
        .mapToInt(e -> e)
        .toArray();
  }

  public void getNumsSameConsecDiff(int cur, int n, int k) {
    if (n == 0) {
      result.add(cur);
      return;
    }

    for (int i = 0; i <= 9; i++) {
      int prev = cur % 10;

      if (Math.abs(prev - i) == k) {
        getNumsSameConsecDiff(cur * 10 + i, n - 1, k);
      }
    }
  }
}