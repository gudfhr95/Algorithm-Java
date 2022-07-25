class Solution {

  boolean[] visited = new boolean[8];
  int result = 0;

  public int solution(int k, int[][] dungeons) {
    travel(dungeons, 0, k);

    return result;
  }

  public void travel(int[][] dungeons, int n, int k) {
    if (n == dungeons.length) {
      result = Math.max(result, n);
      return;
    }

    for (int i = 0; i < dungeons.length; i++) {
      if (visited[i]) {
        continue;
      }

      if (k >= dungeons[i][0]) {
        visited[i] = true;
        travel(dungeons, n + 1, k - dungeons[i][1]);
        visited[i] = false;
      }
    }

    result = Math.max(result, n);
  }
}