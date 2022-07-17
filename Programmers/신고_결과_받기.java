import java.util.*;

class Solution {

  public int[] solution(String[] id_list, String[] report, int k) {
    Set<String> reducedReport = new HashSet<>();
    Map<String, Set<String>> stat = new HashMap<>();
    Map<String, Integer> result = new LinkedHashMap<>();

    for (String r : report) {
      reducedReport.add(r);
    }

    int idx = 0;
    for (String id : id_list) {
      stat.put(id, new HashSet<>());
      result.put(id, 0);
    }

    for (String r : reducedReport) {
      String[] l = r.split(" ");

      stat.get(l[1]).add(l[0]);
    }

    for (String r : reducedReport) {
      String[] l = r.split(" ");

      if (stat.get(l[1]).size() >= k) {
        int sum = result.get(l[0]) + 1;
        result.put(l[0], sum);
      }
    }

    int[] answer = new int[id_list.length];
    int index = 0;
    for (String key : result.keySet()) {
      answer[index++] = result.get(key);
    }

    return answer;
  }
}