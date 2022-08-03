import java.util.*;

class Solution {

  TreeMap<String, Integer> time = new TreeMap<>();
  HashMap<String, Integer> in = new HashMap<>();

  public int[] solution(int[] fees, String[] records) {
    for (String record : records) {
      String[] splited = record.split(" ");

      if (splited[2].equals("IN")) {
        in.put(splited[1], getMinutes(splited[0]));
      } else {
        int inMinute = in.get(splited[1]);

        int totalTime = 0;
        if (time.containsKey(splited[1])) {
          totalTime += time.get(splited[1]);
        }
        totalTime += (getMinutes(splited[0]) - inMinute);

        time.put(splited[1], totalTime);
        in.remove(splited[1]);
      }
    }

    for (Map.Entry<String, Integer> entry : in.entrySet()) {
      int totalTime = 0;
      if (time.containsKey(entry.getKey())) {
        totalTime += time.get(entry.getKey());
      }
      totalTime += (getMinutes("23:59") - entry.getValue());

      time.put(entry.getKey(), totalTime);
    }

    System.out.println(time);

    return time.values().stream()
        .map((m) -> getFee(fees, m))
        .mapToInt(Number::intValue)
        .toArray();
  }

  public int getMinutes(String s) {
    String[] splited = s.split(":");
    int hour = Integer.parseInt(splited[0]);
    int minute = Integer.parseInt(splited[1]);

    return hour * 60 + minute;
  }

  public int getFee(int[] fees, int minutes) {
    int m = Math.max(minutes - fees[0], 0);
    m = (int) Math.ceil(m / (double) fees[2]);
    return fees[1] + m * fees[3];
  }
}