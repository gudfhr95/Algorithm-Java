class Solution {

  int[][] d;

  public int maximumScore(int[] nums, int[] multipliers) {
    d = new int[multipliers.length][nums.length];

    for (int[] d1 : d) {
      Arrays.fill(d1, Integer.MIN_VALUE);
    }

    return maximumScoreRec(0, 0, nums, multipliers);
  }

  public int maximumScoreRec(int n, int left, int[] nums, int[] multipliers) {
    if (n == multipliers.length) {
      return 0;
    }

    if (d[n][left] != Integer.MIN_VALUE) {
      return d[n][left];
    }

    int l = multipliers[n] * nums[left] + maximumScoreRec(n + 1, left + 1, nums, multipliers);
    int r = multipliers[n] * nums[nums.length - 1 - (n - left)] + maximumScoreRec(n + 1, left, nums,
        multipliers);

    return d[n][left] = Math.max(l, r);
  }
}