class Solution {

  public int solution(int left, int right) {
    int answer = 0;
    for (int i = left; i <= right; i++) {
      if (isSquare(i)) {
        answer -= i;
      } else {
        answer += i;
      }
    }

    return answer;
  }

  public boolean isSquare(int n) {
    for (int i = 1; i * i <= n; i++) {
      if (i * i == n) {
        return true;
      }
    }

    return false;
  }
}