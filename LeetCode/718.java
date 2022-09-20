class Solution {

  public int findLength(int[] nums1, int[] nums2) {
    int[][] d = new int[nums1.length + 1][nums2.length + 1];

    int result = 0;
    for (int i = 1; i <= nums1.length; i++) {
      for (int j = 1; j <= nums2.length; j++) {
        if (nums1[i - 1] == nums2[j - 1]) {
          d[i][j] = d[i - 1][j - 1] + 1;
        }

        result = Math.max(result, d[i][j]);
      }
    }

    return result;
  }
}