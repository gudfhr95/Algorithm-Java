class Solution {

  static int[] dx1 = {0, 1, 0, -1};
  static int[] dy1 = {1, 0, -1, 0};
  static int[] dx2 = {0, 1, 2, 1, 0, -1, -2, -1};
  static int[] dy2 = {-2, -1, 0, 1, 2, 1, 0, -1};

  public int[] solution(String[][] places) {
    int[] result = new int[places.length];
    for (int i = 0; i < places.length; i++) {
      if (check(places[i])) {
        result[i] = 1;
      }
    }

    return result;
  }

  public boolean check(String[] place) {
    for (int y = 0; y < place.length; y++) {
      for (int x = 0; x < place[0].length(); x++) {
        if (place[y].charAt(x) == 'P' && !checkDistance(place, x, y)) {
          return false;
        }
      }
    }

    return true;
  }

  public boolean checkDistance(String[] place, int x, int y) {
    for (int k = 0; k < 4; k++) {
      int xn = x + dx1[k];
      int yn = y + dy1[k];

      if (xn < 0 || yn < 0 || yn > place.length - 1 || xn > place[0].length() - 1) {
        continue;
      }

      if (place[yn].charAt(xn) == 'P') {
        return false;
      }
    }

    for (int k = 0; k < 8; k++) {
      int xn = x + dx2[k];
      int yn = y + dy2[k];

      if (xn < 0 || yn < 0 || yn > place.length - 1 || xn > place[0].length() - 1) {
        continue;
      }

      if (place[yn].charAt(xn) == 'P' && !hasPartition(place, x, y, xn, yn)) {
        return false;
      }
    }

    return true;
  }

  public boolean hasPartition(String[] place, int x1, int y1, int x2, int y2) {
    int xDiff = x2 - x1;
    int yDiff = y2 - y1;

    if (xDiff % 2 == 0) {
      xDiff /= 2;
    }

    if (yDiff % 2 == 0) {
      yDiff /= 2;
    }

    int xt1 = x1 + xDiff;
    int yt1 = y1;
    int xt2 = x1;
    int yt2 = y1 + yDiff;

    char p1 = place[yt1].charAt(xt1);
    char p2 = place[yt2].charAt(xt2);

    if (xt1 == x1 && yt1 == y1) {
      return p2 == 'X';
    }

    if (xt2 == x1 && yt2 == y1) {
      return p1 == 'X';
    }

    return (p1 == 'X') && (p2 == 'X');
  }
}