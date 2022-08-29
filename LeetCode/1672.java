class Solution {

  public int maximumWealth(int[][] accounts) {
    int result = Integer.MIN_VALUE;

    for (int y = 0; y < accounts.length; y++) {
      int sum = 0;
      for (int x = 0; x < accounts[y].length; x++) {
        sum += accounts[y][x];
      }

      result = Math.max(result, sum);
    }

    return result;
  }
}