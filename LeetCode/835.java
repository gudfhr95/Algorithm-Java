class Solution {

  public int largestOverlap(int[][] img1, int[][] img2) {
    int result = 0;
    for (int dy = -img1.length; dy <= img1.length; dy++) {
      for (int dx = -img1.length; dx <= img1.length; dx++) {
        result = Math.max(result, overlap(img1, img2, dx, dy));
      }
    }

    return result;
  }

  public int overlap(int[][] img1, int[][] img2, int dx, int dy) {
    int result = 0;

    for (int y = 0; y < img1.length; y++) {
      for (int x = 0; x < img1.length; x++) {
        if (x + dx < 0 || x + dx >= img1.length) {
          continue;
        }

        if (y + dy < 0 || y + dy >= img1.length) {
          continue;
        }

        if (img1[y][x] == 1 && img2[y + dy][x + dx] == 1) {
          result += 1;
        }
      }
    }

    return result;
  }
}