import java.util.*;

class Solution {

  public String[] solution(int[][] line) {
    ArrayList<ArrayList<Long>> intersections = new ArrayList<>();
    for (int i = 0; i < line.length; i++) {
      for (int j = i + 1; j < line.length; j++) {
        long[] intersection = getIntersection(line[i], line[j]);

        if ((intersection[1] == 0) || (intersection[0] % intersection[1] != 0)) {
          continue;
        }

        if ((intersection[3] == 0) || (intersection[2] % intersection[3] != 0)) {
          continue;
        }

        System.out.printf("%d %d %d %d\n", intersection[0], intersection[1], intersection[2],
            intersection[3]);

        ArrayList<Long> in = new ArrayList<>();
        in.add(intersection[0] / intersection[1]);
        in.add(intersection[2] / intersection[3]);
        intersections.add(in);
      }
    }

    long maxX = Long.MIN_VALUE;
    long minX = Long.MAX_VALUE;
    long maxY = Long.MIN_VALUE;
    long minY = Long.MAX_VALUE;
    for (ArrayList<Long> in : intersections) {
      maxX = Math.max(maxX, in.get(0));
      minX = Math.min(minX, in.get(0));
      maxY = Math.max(maxY, in.get(1));
      minY = Math.min(minY, in.get(1));
    }

    System.out.printf("%d %d %d %d\n", maxX, minX, maxY, minY);

    char[][] result = new char[(int) maxY - (int) minY + 1][(int) maxX - (int) minX + 1];
    for (char[] r1 : result) {
      Arrays.fill(r1, '.');
    }

    for (ArrayList<Long> in : intersections) {
      result[(int) (maxY - in.get(1))][(int) (in.get(0) - minX)] = '*';
    }

    String[] answer = new String[(int) (maxY - minY + 1)];
    for (int i = 0; i < result.length; i++) {
      answer[i] = new String(result[i]);
    }

    return answer;
  }

  public long[] getIntersection(int[] line1, int[] line2) {
    long xN = ((long) line1[1] * line2[2] - (long) line1[2] * line2[1]);
    long xD = ((long) line1[0] * line2[1] - (long) line1[1] * line2[0]);
    long yN = ((long) line1[2] * line2[0] - (long) line1[0] * line2[2]);
    long yD = ((long) line1[0] * line2[1] - (long) line1[1] * line2[0]);

    return new long[]{xN, xD, yN, yD};
  }
}