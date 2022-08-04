class Solution {

  int score = 0;
  int[] result = {-1};

  public int[] solution(int n, int[] info) {
    for (int i = 0; i < (1 << 11); i++) {
      shot(i, n, info);
    }

    return result;
  }

  public void shot(int s, int n, int[] info) {
    boolean[] canShot = getCanShot(s);
    int[] currentShot = new int[11];

    int totalScore = 0;
    int totalShot = 0;
    for (int i = 0; i <= 10; i++) {
      if (canShot[i]) {
        totalScore += (10 - i);
        totalShot += (info[i] + 1);
        currentShot[i] = (info[i] + 1);
      } else {
        if (info[i] > 0) {
          totalScore -= (10 - i);
        }
      }
    }

    if (totalShot > n) {
      return;
    }

    if (totalScore > score) {
      if (totalShot < n) {
        currentShot[10] += (n - totalShot);
      }

      score = totalScore;
      result = currentShot;
    } else if (score > 0 && totalScore == score) {
      if (isLess(currentShot)) {
        result = currentShot;
      }
    }
  }

  public boolean[] getCanShot(int n) {
    boolean[] result = new boolean[11];

    int index = 10;
    while (n > 0) {
      if ((n & 1) == 1) {
        result[index] = true;
      }

      n >>= 1;
      index -= 1;
    }

    return result;
  }

  public boolean isLess(int[] currentShot) {
    for (int i = 10; i >= 0; i--) {
      if (currentShot[i] == result[i]) {
        continue;
      }

      return currentShot[i] > result[i];
    }

    return true;
  }
}