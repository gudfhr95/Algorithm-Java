class Solution {

  public int[] solution(int n, long left, long right) {
    int[] result = new int[(int) (right - left + 1)];

    int index = 0;
    for (long i = left; i <= right; i++) {
      long x = (i % n) + 1;
      long y = (i / n) + 1;

      result[index++] = (int) Math.max(x, y);
    }

    return result;
  }
}
