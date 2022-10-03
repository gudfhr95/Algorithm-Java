class Solution {

  public int minCost(String colors, int[] neededTime) {
    int result = 0;

    int startIndex = 0;
    int endIndex = 0;
    while (startIndex < colors.length()) {
      while (endIndex < colors.length() && colors.charAt(startIndex) == colors.charAt(endIndex)) {
        endIndex += 1;
      }

      int sum = 0;
      int maxTime = 0;
      for (int i = startIndex; i < endIndex; i++) {
        sum += neededTime[i];
        maxTime = Math.max(maxTime, neededTime[i]);
      }

      result += (sum - maxTime);

      startIndex = endIndex;
    }

    return result;
  }
}