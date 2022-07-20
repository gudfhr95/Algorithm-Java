class Solution {

  public int solution(int[][] sizes) {
    int maxWidth = sizes[0][0];
    int maxHeight = sizes[0][1];

    for (int i = 1; i < sizes.length; i++) {
      int s1 = Math.max(maxWidth, sizes[i][0]) * Math.max(maxHeight, sizes[i][1]);
      int s2 = Math.max(maxWidth, sizes[i][1]) * Math.max(maxHeight, sizes[i][0]);

      if (s1 < s2) {
        maxWidth = Math.max(maxWidth, sizes[i][0]);
        maxHeight = Math.max(maxHeight, sizes[i][1]);
      } else {
        maxWidth = Math.max(maxWidth, sizes[i][1]);
        maxHeight = Math.max(maxHeight, sizes[i][0]);
      }
    }

    return maxWidth * maxHeight;
  }
}