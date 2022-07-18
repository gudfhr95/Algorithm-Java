class Solution {

  public int solution(int[] numbers) {
    boolean[] check = new boolean[10];

    for (int n : numbers) {
      check[n] = true;
    }

    int result = 0;
    for (int i = 0; i < check.length; i++) {
      if (!check[i]) {
        result += i;
      }
    }

    return result;
  }
}