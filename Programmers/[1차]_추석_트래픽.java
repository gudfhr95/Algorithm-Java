import java.util.*;

class Solution {

  PriorityQueue<Integer> q = new PriorityQueue<>();
  int result = 0;

  public int solution(String[] lines) {
    int[][] timelines = new int[lines.length][2];
    for (int i = 0; i < lines.length; i++) {
      String[] splited = lines[i].split(" ");
      int end = getEnd(splited[1]);
      int start = end - getProcessTime(splited[2]) + 1;

      timelines[i][0] = start;
      timelines[i][1] = end;
    }

    Arrays.sort(timelines, (t1, t2) -> {
      if (t1[0] == t2[0]) {
        return t1[1] - t2[1];
      }

      return t1[0] - t2[0];
    });

    for (int i = 0; i < timelines.length; i++) {
      getMaxTraffic(timelines[i][0], timelines[i][1]);
    }

    return result;
  }

  public int getEnd(String s) {
    int result = 0;

    String[] splited = s.split(":");
    result += Integer.parseInt(splited[0]) * 60 * 60 * 1000;
    result += Integer.parseInt(splited[1]) * 60 * 1000;
    result += (int) (Double.parseDouble(splited[2]) * 1000);

    return result;
  }

  public int getProcessTime(String s) {
    int result = 0;

    s = s.substring(0, s.length() - 1);
    result += (int) (Double.parseDouble(s) * 1000);

    return result;
  }

  public void getMaxTraffic(int start, int end) {
    if (q.isEmpty()) {
      q.add(end);
      result = Math.max(result, q.size());
      return;
    }

    int minTime = start - 1000;
    while (!q.isEmpty() && q.peek() <= minTime) {
      q.remove();
    }

    q.add(end);
    result = Math.max(result, q.size());
    return;
  }
}