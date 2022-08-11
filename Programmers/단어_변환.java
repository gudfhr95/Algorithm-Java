import java.util.*;

class Solution {

  Map<String, Integer> d = new HashMap<>();

  public int solution(String begin, String target, String[] words) {
    for (String word : words) {
      d.put(word, -1);
    }

    if (!d.containsKey(target)) {
      return 0;
    }

    bfs(begin);

    return d.get(target);
  }

  public void bfs(String start) {
    Queue<String> q = new LinkedList<>();

    q.add(start);
    d.put(start, 0);

    while (!q.isEmpty()) {
      String cur = q.remove();
      int dist = d.get(cur);

      for (int i = 0; i < cur.length(); i++) {
        for (int c = 0; c < 26; c++) {
          StringBuilder next = new StringBuilder(cur);
          next.setCharAt(i, (char) ('a' + c));

          if (!d.containsKey(next.toString())) {
            continue;
          }

          if (d.get(next.toString()) != -1) {
            continue;
          }

          q.add(next.toString());
          d.put(next.toString(), dist + 1);
        }
      }
    }
  }
}