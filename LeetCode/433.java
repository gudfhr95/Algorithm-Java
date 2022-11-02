class Solution {

  public int minMutation(String start, String end, String[] bank) {
    Map<String, Integer> d = new HashMap<>();

    Queue<String> q = new LinkedList<>();
    q.add(start);
    d.put(start, 0);

    while (!q.isEmpty()) {
      String cur = q.remove();

      for (int i = 0; i < bank.length; i++) {
        if (d.containsKey(bank[i])) {
          continue;
        }

        if (!isMutation(cur, bank[i])) {
          continue;
        }

        q.add(bank[i]);
        d.put(bank[i], d.get(cur) + 1);
      }
    }

    if (!d.containsKey(end)) {
      return -1;
    }

    return d.get(end);
  }

  public boolean isMutation(String s1, String s2) {
    int count = 0;
    for (int i = 0; i < s1.length(); i++) {
      if (s1.charAt(i) != s2.charAt(i)) {
        count += 1;
      }
    }

    return count == 1;
  }
}