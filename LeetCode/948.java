class Solution {

  public int bagOfTokensScore(int[] tokens, int power) {
    int result = 0;

    Arrays.sort(tokens);

    int left = 0;
    int right = tokens.length - 1;
    int score = 0;
    while (left <= right && (power >= tokens[left] || score > 0)) {
      while (left <= right && power >= tokens[left]) {
        power -= tokens[left++];
        score += 1;
      }

      result = Math.max(result, score);
      if (left <= right && score > 0) {
        power += tokens[right--];
        score -= 1;
      }
    }

    return result;
  }
}