class Solution {

  final int[] dx = {0, 1, 0, -1};
  final int[] dy = {-1, 0, 1, 0};

  public List<List<Integer>> pacificAtlantic(int[][] heights) {
    List<List<Integer>> result = new ArrayList<>();

    for (int y = 0; y < heights.length; y++) {
      for (int x = 0; x < heights[y].length; x++) {
        if (canGo(heights, x, y)) {
          result.add(makePair(x, y));
        }
      }
    }

    Collections.sort(result, (e1, e2) -> {
      if (e1.get(0) == e2.get(0)) {
        return e1.get(1) - e2.get(1);
      }

      return e1.get(0) - e2.get(0);
    });

    return result;
  }

  public boolean canGo(int[][] heights, int x, int y) {
    Queue<List<Integer>> q = new LinkedList<>();
    boolean[][] visited = new boolean[heights.length][heights[0].length];

    q.add(makePair(x, y));
    visited[y][x] = true;

    boolean pacific = false;
    boolean atlantic = false;

    while (!q.isEmpty()) {
      List<Integer> cur = q.poll();

      for (int k = 0; k < 4; k++) {
        int xn = cur.get(1) + dx[k];
        int yn = cur.get(0) + dy[k];

        if (xn < 0 || yn < 0) {
          pacific = true;
          continue;
        }

        if (xn >= heights[0].length || yn >= heights.length) {
          atlantic = true;
          continue;
        }

        if (visited[yn][xn]) {
          continue;
        }

        if (heights[cur.get(0)][cur.get(1)] < heights[yn][xn]) {
          continue;
        }

        q.add(makePair(xn, yn));
        visited[yn][xn] = true;
      }
    }

    return pacific && atlantic;
  }

  public List<Integer> makePair(int x, int y) {
    List<Integer> result = new ArrayList<>();

    result.add(y);
    result.add(x);

    return result;
  }
}