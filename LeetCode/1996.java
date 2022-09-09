class Solution {

  public int numberOfWeakCharacters(int[][] properties) {
    Arrays.sort(properties, (e1, e2) -> {
      if (e1[0] == e2[0]) {
        return e1[1] - e2[1];
      }

      return e2[0] - e1[0];
    });

    int result = 0;

    int maxDefense = 0;
    for (int[] property : properties) {
      maxDefense = Math.max(maxDefense, property[1]);

      if (property[1] < maxDefense) {
        result += 1;
      }
    }

    return result;
  }
}