import java.util.*;

class Solution {

  int size;

  Map<String, Integer> index = new HashMap<>();
  Map<String, List<String>> map = new HashMap<>();
  int[][] used = new int[10000][10000];

  public ArrayList<String> solution(String[][] tickets) {
    size = tickets.length;

    int idx = 0;
    for (String[] ticket : tickets) {
      if (!index.containsKey(ticket[0])) {
        index.put(ticket[0], idx++);
      }

      if (!index.containsKey(ticket[1])) {
        index.put(ticket[1], idx++);
      }

      if (!map.containsKey(ticket[0])) {
        map.put(ticket[0], new ArrayList<>());
      }

      map.get(ticket[0]).add(ticket[1]);
      used[index.get(ticket[0])][index.get(ticket[1])] += 1;
    }

    for (List<String> value : map.values()) {
      Collections.sort(value);
    }

    return dfs("ICN", new ArrayList<String>());
  }

  public ArrayList<String> dfs(String start, ArrayList<String> path) {
    if (!map.containsKey(start) && path.size() != size) {
      return null;
    }

    if (path.size() == size) {
      path.add(start);
      return path;
    }

    path.add(start);

    for (String next : map.get(start)) {
      int startIndex = index.get(start);
      int nextIndex = index.get(next);

      if (used[startIndex][nextIndex] == 0) {
        continue;
      }

      used[startIndex][nextIndex] -= 1;

      ArrayList<String> result = dfs(next, (ArrayList<String>) path.clone());
      if (result != null) {
        return result;
      }

      used[startIndex][nextIndex] += 1;
    }

    return null;
  }
}